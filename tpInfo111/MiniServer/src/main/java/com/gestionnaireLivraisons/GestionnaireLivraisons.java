package com.gestionnaireLivraisons;

import com.atoudeft.commun.evenement.Arguments;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * La classe pour gérer les erreurs d'identification des clients
 */
class AuthenticationException extends Exception {
}

/**
 * La classe qui permet de gérer des livraisons selon des évènements envoyés par un livreur (client).
 */
public class GestionnaireLivraisons implements GestionnaireEvenement {
    // Emplacement du fichier contenant la liste des livreurs enregistrés.
    final private static String fichierLivreurs = "tpInfo111\\MiniServer\\src\\main\\livreurs.txt";

    // Attributs d'ínstance pour un GestionnaireLivraisons
    final private IListeChaineeLivreurs livreursEnregistres;
    final private Hashtable<Connexion, Livreur> livreursAuthentifies;
    final private FilePrioriteLivraisons livraisonsAEffectuer;
    final private IListeLivraisons livraisonsEchouees;

    final private ArrayList<String> messagesId;

    /**
     * Construit un gestionnaire de livraisons.
     *
     */
    public GestionnaireLivraisons() {
        this.livreursEnregistres = new ListeChaineeLivreurs();
        this.lireFichierLivreurs();

        this.livreursAuthentifies = new Hashtable<>();
        this.livraisonsAEffectuer = LivraisonFactory.populateFileLivraisons();
        this.livraisonsEchouees = new ListeLivraisons();

        this.messagesId = new ArrayList<>();
    }

    /**
     * Lit le fichier des livreurs enregistrés.
     */
    private void lireFichierLivreurs() {
        try {
            List<String> lignes = Files.readAllLines(Path.of(GestionnaireLivraisons.fichierLivreurs), StandardCharsets.UTF_8);

            for (String ligne : lignes) {
                ligne = ligne.trim();
                if (ligne.charAt(0) != '#') {   //  ignorer les commentaires.
                    Arguments args = new Arguments(new Evenement(null, null, ligne));
                    int idLivreur = Integer.parseInt(args.extraireArgumentSuivant());
                    String typeLivreur = args.extraireArgumentSuivant().toUpperCase();
                    String nomLivreur = args.lire();
                    Livreur livreur;

                    // Créer le livreur avec le constructeur approprié
                    switch (typeLivreur) {
                        case "VELO":
                            // DONE : À compléter/modifier
                            livreur = new LivreurVelo(idLivreur,nomLivreur);
                            break;
                        case "CAMION":
                            // DONE : À compléter/modifier
                            livreur = new LivreurCamion(idLivreur,nomLivreur);
                            break;
                        case "VOITURE":
                            // DONE : À compléter/modifier
                            livreur = new LivreurVoiture(idLivreur,nomLivreur);
                            break;
                        default:
                            throw new IOException();
                    }
                    this.livreursEnregistres.ajouter(livreur);
                    System.out.println(ligne);
                }
            }
        } catch (IOException | ListeChaineeException e) {
            System.err.println("ERREUR dans la lecture du fichier de livreurs.");
            System.exit(-1);
        }
    }

    /**
     * Écrit le fichier des livreurs enregistrés.
     */
    private void ecrireFichierLivreurs() {
        try {
            StringBuilder contenu = new StringBuilder();

            for (Livreur Livreur : this.livreursEnregistres.toArray()) {
                contenu.insert(0, Livreur + "\n");
            }
            contenu.insert(0, "#  structure <id livreur> <type livreur> <nom livreur>\n");

            Files.writeString(Path.of(GestionnaireLivraisons.fichierLivreurs), contenu.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("ERREUR dans l'écriture du fichier de livreurs.");
            System.exit(-1);
        }
    }

    /**
     * Lance la sauvegarde des livreurs enregistrés avant de quitter le serveur.
     *
     */
    public void quitter() {
        this.ecrireFichierLivreurs();
    }

    /**
     * Affiche l'ensemble des livraisons à effectuer.
     *
     */
    public void afficherLivraisonsAEffectuer() {
        System.out.println("Liste des livraisons à effectuer :");
        System.out.println("(id, lot, priorité, tentative)");
        this.livraisonsAEffectuer.afficher();
    }

    /**
     * Affiche des statistiques concernant les livraisons.
     *
     */
    public void afficherStatistiques() {
        // DONE : À compléter/modifier
        System.out.println("Livraisons en attente : " + this.livraisonsAEffectuer.taille());
        System.out.println("Livraisons échouées : " + this.livraisonsEchouees.taille());
        System.out.println("Livreurs authentifiés : " + this.livreursAuthentifies.size());
        System.out.println("Livreurs enregistrés : " + this.livreursEnregistres.taille());
        System.out.println("Livraisons en cours par livreur :");
        Enumeration<Connexion> connexions = this.livreursAuthentifies.keys();
        while (connexions.hasMoreElements()) {
            Connexion cnx = connexions.nextElement();
            Livreur livreur = this.livreursAuthentifies.get(cnx);
            System.out.println(livreur.getNom() + " : "
                    + livreur.nbLivraisonsEnCours() + " en cours");
        }
    }


    /**
     * Applique la commande EXIT envoyée par un client.
     *
     * @param evenement L'évènement reçu.
     * @return le message "END" au clien.
     */
    private String traiterEXIT(Evenement evenement) {
        // DON : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        Livreur livreur = this.livreursAuthentifies.get(connexion);

        // Si le livreur est authentifié
        if (livreur != null) {
            // Récupérer toutes ses livraisons en cours et les remettre dans la file
            IListeLivraisons livraisonsNonTraitees = livreur.supprimerToutesLesLivraisons();

            for (Livraison livraison : livraisonsNonTraitees) {
                livraison.setStatut(Statut.EN_ATTENTE);
                this.livraisonsAEffectuer.ajouter(livraison);
            }

            // Retirer le livreur des authentifiés
            this.livreursAuthentifies.remove(connexion);
        }

        return "END";
    }

    /**
     * Applique la commande ID envoyée par un client.
     *
     * @param evenement L'évènement reçu.
     * @return La chaîne à renvoyer au client.
     */
    private String traiterID(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        Arguments arguments = new Arguments(evenement);
        String strID= arguments.extraireArgumentSuivant();

        //verification de l'argument
        if(strID==null){
            return "BAD_ARGUMENT_ERROR";
        }
        int idLivreur;

        //transforme en integers une chaine de character et si ce n'est pas possible il renvoi une erreur
        try{
            idLivreur=Integer.parseInt(strID);
        }catch (NumberFormatException e){
            return "BAD_ARGUMENT_ERROR";
        }

        //verification si le livreur est enregistre
        Livreur livreur= this.livreursEnregistres.rechercher(idLivreur);
        if(livreur == null){
            return "AUTHENTICATION_ERROR"; // il n'est pas existant dans la base de donnee
        }

        //verification pour voir si le livreur est deja connecter a un autre key
        if(this.livreursAuthentifies.containsValue(livreur)){
            return "TOO_MANY_CONNECTIONS_ERROR"; //il est deja connecter
        }

        //verification si connexion (key) est deja utiliser par un autre livreur
        if(this.livreursAuthentifies.containsKey(connexion)){
            return "ALREADY_AUTHENTIFIED_ERROR"; // session a deja un user
        }

        //Authentification réussie
        this.livreursAuthentifies.put(connexion, livreur);
        return "AUTHORIZED " + idLivreur + " " + livreur.getNom();
    }

    /**
     * Applique la commande REGISTER envoyée par le client.
     *
     * @param evenement L'évènement reçu.
     * @return La chaîne à renvoyer au client.
     */
    private String traiterREGISTER(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        //REGISTER <id_livreur > <mode_livraison> <nom_livreur>.
        Arguments args = new Arguments(evenement);
        String strIdLivreur = args.extraireArgumentSuivant();
        String modeLivraison = args.extraireArgumentSuivant();
        String  nomLivreur = args.lire();

        if (strIdLivreur == null || modeLivraison == null || nomLivreur == null || nomLivreur.isEmpty()){
            return "BAD_ARGUMENT_ERROR";
        }

        int idLivreur;
        try{
            idLivreur = Integer.parseInt(strIdLivreur);
        }catch (NumberFormatException e){
            return "BAD_ARGUMENT_ERROR";
        }

        if (this.livreursEnregistres.rechercher(idLivreur) != null){
            return "ALREADY_REGISTERED_ERROR";
        }

        Livreur livreur;
        switch (modeLivraison.toUpperCase()){
            case "VELO":
                livreur = new LivreurVelo(idLivreur,nomLivreur);
                break;
            case "VOITURE":
                livreur = new LivreurVoiture(idLivreur,nomLivreur);
                break;
            case "CAMION":
                livreur = new LivreurCamion(idLivreur, nomLivreur);
                break;
            default:
                return "BAD_ARGUMENT_ERROR";
        }

        try {
            this.livreursEnregistres.ajouter(livreur);
        }catch (ListeChaineeException e){
            return "BAD_ARGUMENT_ERROR";
        }

        return "REGISTERED " + idLivreur + " " + nomLivreur;
    }

    /**
     * Applique la commande GET envoyée par un client.
     *
     * @param evenement L'évènement reçu.
     * @return La chaîne à renvoyer au client.
     */
    private String traiterGET(Evenement evenement) {
        // done : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();

        Livreur livreur = this.livreursAuthentifies.get(connexion);
        if (livreur == null){
            return "AUTHENTICATION_ERROR";
        }

        int capacite = livreur.capaciteLivraison();
        int nbDejaPresent = livreur.nbLivraisonsEnCours();
        int nbAAttribuer = capacite-nbDejaPresent;

        if (this.livraisonsAEffectuer.estVide() || nbAAttribuer <= 0){
            return "EMPTY";
        }

        StringBuilder deliveries = new StringBuilder();
        int nbAjoute = 0;

        while(nbAjoute < nbAAttribuer && !this.livraisonsAEffectuer.estVide()){
            Livraison livraison = this.livraisonsAEffectuer.retirer();
            livraison.setStatut(Statut.EN_COURS);
            livreur.ajouterLivraisonEnCours(livraison);
            deliveries.append(" ").append(livraison.getId())
                    .append(" ").append(livraison.getLot())
                    .append(" ").append(livraison.getPriorite())
                    .append(" ").append(livraison.getTentative());
            nbAjoute++;
        }
        return "DELIVERIES " + nbAjoute + deliveries;
    }

    /**
     * Applique la commande DELIVERED envoyée par un client.
     *
     * @param evenement L'évènement reçu.
     * @return La chaîne à renvoyer au client.
     */
    private String traiterDELIVERED(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        // vériffication conetction
        Livreur livreur = this.livreursAuthentifies.get(connexion);
        if (livreur == null){
            return "AUTHENTICATION_ERROR";
        }
        // vériffier valeur id de la livraison
        Arguments arguments = new Arguments(evenement);
        String strId = arguments.extraireArgumentSuivant();
        if (strId == null) {
            return "BAD_ARGUMENT_ERROR";
        }
        // convertie l'id en int
        int idLivraison;
        try {
            idLivraison = Integer.parseInt(strId);
        } catch (NumberFormatException e) {  // si pas un chiffre l'ever exception
            return "BAD_ARGUMENT_ERROR";
        }
        // permt de vériffier que c'est le bon livreur
        Livraison livraison = livreur.supprimerLivraisonEnCours(idLivraison);
        if (livraison == null) {
            return "BAD_DELIVERY_ERROR";
        }
        // change les statu
        livraison.setStatut(Statut.LIVREE);
        livreur.ajouterLivraisonEffectuee(livraison);

        return "DELIVERED_OK " + idLivraison;
    }

    /**
     * Applique la commande FAILED envoyée par un client.
     *
     * @param evenement L'évènement reçu.
     * @return La chaîne à renvoyer au client.
     */
    private String traiterFAILED(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion= (Connexion) evenement.getSource();
        Livreur livreur = this.livreursAuthentifies.get(connexion);

        if(livreur==null){
            return "AUTHENTICATION_ERROR";
        }

        Arguments arguments= new Arguments(evenement);
        String srtID = arguments.extraireArgumentSuivant();

        if(srtID==null){
            return "BAD_ARGUMENT_ERROR";
        }

        int idLivraison;

        try{
            idLivraison=Integer.parseInt(srtID);
        }catch (NumberFormatException e){
            return "BAD_ARGUMENT_ERROR";
        }

        Livraison livraison= livreur.supprimerLivraisonEnCours(idLivraison);

        if(livraison==null){
            return "BAD_DELIVERY_ERROR";
        }

        livraison.nouvelleTentative();

        if(livraison.resteTentatives()){
            livraison.setStatut(Statut.EN_ATTENTE);
            this.livraisonsAEffectuer.ajouter(livraison);
            return "FAILED_CONTINUE " + idLivraison;
        }else{
            livraison.setStatut(Statut.ECHOUEE);
            this.livraisonsEchouees.ajouter(livraison);
            return "FAILED_ABORT " + idLivraison;
        }
    }

    /**
     * Calcule et retourne le revenu produit par un livreur.
     *
     * @param evenement L'évènement reçu.
     */
    private String traiterINCOME(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        // vériffication conetction
        Livreur livreur = this.livreursAuthentifies.get(connexion);
        if (livreur == null){
            return "AUTHENTICATION_ERROR";
        }
        // calcule du revnue
        double revenu = livreur.calculerRevenu();
        int nbLivraisons = livreur.nbLivraisonsEffectuees();

        return "REVENU " + revenu + " " + nbLivraisons;
    }

    /**
     * Retourne toutes les livraisons en cours pour le livreur concerné.
     *
     * @param evenement Événement de type INFO à traiter.
     * @return La chaine constituant la réponse à retourner au client.
     */
    private String traiterINFO(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();

        Livreur livreur = this.livreursAuthentifies.get(connexion);
        if (livreur == null){
            return "AUTHENTICATION_ERROR";
        }

        Arguments args = new Arguments(evenement);
        String  argument = args.extraireArgumentSuivant();

        if (argument == null){
            if (!livreur.aDesLivraisonsEnCours()){
                return "NO_DELIVERY_ERROR";
            }
            StringBuilder deliveries = new StringBuilder();
            int nbAjoute = 0;

            Iterator<Livraison> iterator = livreur.donneIterateurLivraisonsEnCours();
            while (iterator.hasNext()){
                Livraison livraison = iterator.next();
                deliveries.append(" ")
                        .append(livraison.getId()).append(" ")
                        .append(livraison.getLot()).append(" ")
                        .append(livraison.getPriorite()).append(" ")
                        .append(livraison.getTentative());
                nbAjoute++;
            }
            return "DELIVERIES_INFO " + nbAjoute + deliveries;
        }
        else {
            int idLivraison;
            try {
                idLivraison = Integer.parseInt(argument);
            } catch (NumberFormatException e){
                return "BAD_ARGUMENT_ERROR";
            }

            Livraison livraison = livreur.rechercherLivraisonEnCours(idLivraison);
            if (livraison == null){
                return "BAD_DELIVERY_ERROR";
            }

            return "DELIVERIES_INFO 1 " + livraison.getId() + " "+ livraison.getLot() + " "
                    + livraison.getPriorite() + " " + livraison.getTentative();
        }
    }

    /**
     * Le client envoie un message à un autre ou à d'autres livreurs.
     *
     * @param evenement Événement de type INFO à traiter.
     * @return La chaine constituant la réponse à retourner au client.
     */
    private String traiterSEND(Evenement evenement) {
        // DONE : À compléter/modifier
        Connexion connexion = (Connexion) evenement.getSource();
        // vériffication conetction
        Livreur livreur = this.livreursAuthentifies.get(connexion);
        if (livreur == null){
            return "AUTHENTICATION_ERROR";
        }
        // extraire info message
        Arguments args = new Arguments(evenement);
        String idMsg = args.extraireArgumentSuivant();
        String destinataire = args.extraireArgumentSuivant();
        String msg = args.lire();

        if (idMsg==null||destinataire==null||msg==null||msg.isEmpty()){
            return "BAD_ARGUMENT_ERROR";
        }

        // le msg a dégà été traité
        if (this.messagesId.contains(idMsg)) {
            return "ACK " + idMsg;
        }
        // ajoute dans msg traité
        this.messagesId.add(idMsg);
        String msgEnvoyer = "MSG " + livreur.getId() + " " + msg;
        if (destinataire.equals("*")){
            for (Map.Entry<Connexion, Livreur> entry: this.livreursAuthentifies.entrySet()){
                if (!entry.getKey().equals(connexion)){
                    entry.getKey().envoyer(msgEnvoyer);
                }
            }
        }
        else {
            int idDest;
            try {
                idDest= Integer.parseInt(destinataire);
            }
            catch (NumberFormatException e){
                return "BAD_ARGUMENT_ERROR";
            }
            Connexion connexDest = null;
            for (Map.Entry<Connexion, Livreur> entry: this.livreursAuthentifies.entrySet()){
                if (entry.getValue().getId() == idDest) {
                    connexDest =entry.getKey();
                    break;
                }
            }
            if (connexDest==null){
                return "AUTHENTICATION_ERROR";
            }
            connexDest.envoyer(msgEnvoyer);
        }

        return "ACK " + idMsg;
    }

    /**
     * Renvoie un message d'erreur au client pour cause d'évènement inconnu.
     *
     * @return La chaîne à renvoyer au client.
     */
    private String traiterCOMMAND_ERROR() {
        String reponse;

        reponse = "COMMAND_ERROR";

        return reponse;
    }


    /**
     * Gère un évènement reçu en paramètre.
     *
     * @param evenement L'événement a géré.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();

        if (source instanceof Connexion) {
            Connexion cnx = (Connexion) source;
            System.out.println("GEST. LIV. a reçu : " + evenement.getType() + " " + evenement.getArgument());

            String reponse = "";
            switch (evenement.getType().toUpperCase()) {
                case "EXIT": // Le client se déconnecte.
                    reponse = this.traiterEXIT(evenement);
                    break;
                case "REGISTER": // Le client s'enregistre comme nouveau livreur.
                    reponse = this.traiterREGISTER(evenement);
                    break;
                case "ID": // Le client s'identifie.
                    reponse = this.traiterID(evenement);
                    break;
                case "GET": //  Le client a demandé des livraisons à effectuer.
                    reponse = this.traiterGET(evenement);
                    break;
                case "DELIVERED": // Le client informe qu'une livraison a été effectuée.
                    reponse = this.traiterDELIVERED(evenement);
                    break;
                case "FAILED": // Le client informe qu'une livraison n'a pas pu être effectuée.
                    reponse = this.traiterFAILED(evenement);
                    break;
                case "INCOME":
                    reponse = this.traiterINCOME(evenement);
                    break;
                case "INFO":
                    reponse = this.traiterINFO(evenement);
                    break;
                case "SEND":
                    reponse = this.traiterSEND(evenement);
                    break;
                default: // La commande envoyée par le client n'a pas été reconnue.
                    this.traiterCOMMAND_ERROR();
            }

            cnx.envoyer(reponse);
        }
    }
}
