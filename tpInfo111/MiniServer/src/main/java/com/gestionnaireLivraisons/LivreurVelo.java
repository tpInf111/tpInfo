package com.gestionnaireLivraisons;

/**
 * La classe de livreur à vélo
 */
// TODO : À compléter/modifier
public class LivreurVelo extends Livreur{
    public LivreurVelo(int id, String nom) {
        super(id, nom);
    }

    /** Cette méthode calcule le revenu d’un livreur selon les formules suivantes :
     *
     *   Le revenu d’un livreur à vélo est de 5.0$ par livraison
     */
    @Override
    public double calculerRevenu() {
        return this.nbLivraisonsEffectuees()*5.0;
    }

    /** Cette méthode retourne le nombre de livraisons qu’un livreur peut livrer selon le moyen de
     * livraison utilisé. En effet :
     *
     * La capacité d’un livreur par vélo est de deux livraisons
     */
    @Override
    public int capaciteLivraison() {
        return 2;
    }
    /**
     * La méthode String toString(). Il faut redéfinir cette méthode
     * Elle doit retourner une chaîne de caractères incluant la nature du livreur (à vélo),
     * l’identifiant et le nom du livreur
     */
    @Override
    public String toString() {
        return this.getId() + " VELO " +this.getNom();    }
}
