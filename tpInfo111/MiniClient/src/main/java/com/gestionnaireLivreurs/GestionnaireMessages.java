package com.gestionnaireLivreurs;

import com.atoudeft.client.Client;

import java.util.Vector;

public class GestionnaireMessages extends Thread {
    private Client client;
    private int idLivreur;
    Vector<Message> messages;

    public GestionnaireMessages(Client client, int idLivreur) {
        this.client = client;
        this.idLivreur = idLivreur;
        this.messages = new Vector();
    }

    public void ajouter(Message message) {
        messages.add(message);
    }

    public void recuACK() {
        messages.remove(0);
    }

    @Override
    public void run() {
        while (client.isConnecte()) {
            try {
                //  Envoi du message en tête de file
                if (this.messages.size() > 0) {
                    Message msg = this.messages.elementAt(0);
                    String toSend = "SEND " + msg;
                    System.out.println("Gest. Msg. envoie : " + msg);
                    this.client.envoyer(toSend);
                }
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
