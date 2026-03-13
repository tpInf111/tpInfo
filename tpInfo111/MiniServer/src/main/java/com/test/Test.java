package com.test;

import com.gestionnaireLivraisons.ListeChaineeException;

public class Test {
    public static void main(String[] args) throws ListeChaineeException {
        TestFilePrioriteLivraisons.tests();
        System.out.println();

        TestListeChaineeLivreurs.tests();
        System.out.println();

        TestListeLivraisons.tests();
        System.out.println();
    }
}

