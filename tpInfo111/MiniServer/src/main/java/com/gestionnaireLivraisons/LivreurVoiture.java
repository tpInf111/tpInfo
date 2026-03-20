package com.gestionnaireLivraisons;

/**
 * La classe de livreur en voiture
 */
// DONE : À compléter/modifier
public class LivreurVoiture extends Livreur{
    public LivreurVoiture(int id, String nom){
        super(id,nom);
    }

    /** Cette méthode calcule le revenu d’un livreur selon les formules suivantes :
     *
     *   Le revenu d’un livreur en voiture à vélo est de 7.5$ par livraison
     */
    @Override
    public double calculerRevenu(){
        return nbLivraisonsEffectuees()*7.5;
    };

    /** Cette méthode retourne le nombre de livraisons qu’un livreur peut livrer selon le moyen de
     * livraison utilisé. En effet :
     *
     * La capacité d’un livreur par voiture est de cinq livraisons
     */
    @Override
    public int capaciteLivraison(){
        return 5;
    }

    /**
     * La méthode String toString(). Il faut redéfinir cette méthode
     * Elle doit retourner une chaîne de caractères incluant la nature du livreur ( en voiture ),
     * l’identifiant et le nom du livreur
     */
    @Override
    public String toString() {
        return this.getId() + " VOITURE " +this.getNom();    }
}