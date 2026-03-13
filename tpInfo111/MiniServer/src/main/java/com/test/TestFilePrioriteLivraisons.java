package com.test;

import com.gestionnaireLivraisons.FilePrioriteLivraisons;
import com.gestionnaireLivraisons.Livraison;
import com.gestionnaireLivraisons.Priorite;
// TODO : À compléter/modifier

import java.util.ArrayList;
import java.util.List;

public class TestFilePrioriteLivraisons {
    /**
     * Test de la méthode estVide.
     */
    private static FilePrioriteLivraisons fileTest = new FilePrioriteLivraisons();

    private static void testEstVide() {
        System.out.print("Test estVide : "+"\n");

        // DODO : À compléter/modifier
        System.out.println(fileTest.estVide());
    }

    /**
     * Test de la méthode ajouter.
     */
    private static void testAjouter() {
        System.out.print("Test ajouter : "+"\n");

        // DODO : À compléter/modifier
        Livraison livraisonTest = new Livraison(Priorite.URGENTE, 1);
        fileTest.ajouter(livraisonTest);
        fileTest.afficher();
    }

    /**
     * Test de la méthode ajouterTout.
     */
    private static void testAjouterTout() {
        System.out.print("Test ajouterTout : "+"\n");

        // DODO : À compléter/modifier
        List<Livraison> livraisons = new ArrayList<>();

        livraisons.add(new Livraison(Priorite.NORMALE, 3));
        livraisons.add(new Livraison(Priorite.URGENTE, 3));
        livraisons.add(new Livraison(Priorite.NORMALE, 2));
        livraisons.add(new Livraison(Priorite.URGENTE, 2));
        livraisons.add(new Livraison(Priorite.NORMALE, 1));
        livraisons.add(new Livraison(Priorite.URGENTE, 1));
        fileTest.ajouterTout(livraisons);
        fileTest.afficher();
    }

    /**
     * Test de la méthode taille.
     */
    private static void testTaille() {
        System.out.print("Test taille : "+"\n");

        // DODO : À compléter/modifier
        System.out.println("taille real: 7, taille par le méthode: "+fileTest.taille() );
    }

    /**
     * Test de la méthode retirer.
     */
    private static void testRetirer() {
        System.out.print("Test retirer : "+"\n");

        // DODO : À compléter/modifier

        System.out.println("l'élement le plus prioritaire : " + fileTest.retirer());
        System.out.print("élement restant :");
        fileTest.afficher();
    }

    /**
     * Lancements des tests pour la classe FilePrioriteLivraisons.
     */
    public static void tests() {
        System.out.println("----- TEST FilePrioriteLivraisons -----");

        TestFilePrioriteLivraisons.testEstVide();
        TestFilePrioriteLivraisons.testAjouter();
        TestFilePrioriteLivraisons.testAjouterTout();
        TestFilePrioriteLivraisons.testTaille();
        TestFilePrioriteLivraisons.testRetirer();
    }
}
