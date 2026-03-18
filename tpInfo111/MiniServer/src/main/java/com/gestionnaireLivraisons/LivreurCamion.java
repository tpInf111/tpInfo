package com.gestionnaireLivraisons;

/**
 * La classe de livreur en camion
 */
// TODO : À compléter/modifier
public class LivreurCamion extends Livreur{

    public LivreurCamion(int id, String nom){
        super(id, nom);
    }

    /** Cette méthode calcule le revenu d’un livreur selon les formules suivantes :
     *
     *  Le revenu d’un livreur en camion est de 10.0$ par livraison
     */
    @Override
    public double calculerRevenu() {
        return this.nbLivraisonsEffectuees()*10.0;
    }

    /** Cette méthode retourne le nombre de livraisons qu’un livreur peut livrer selon le moyen de
     * livraison utilisé. En effet :
     *
     *  La capacité d’un livreur par camion est de huit livraisons
     */
    @Override
    public int capaciteLivraison(){
        return 8;
    }

    /**
     * La méthode String toString(). Il faut redéfinir cette méthode
     * Elle doit retourner une chaîne de caractères incluant la nature du livreur (par camion),
     * l’identifiant et le nom du livreur
     */
    @Override
    public String toString() {
        return this.getId() + " CAMION " +this.getNom();
    }
}
