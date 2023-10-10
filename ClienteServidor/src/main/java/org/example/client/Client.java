package org.example.client;

import java.io.IOException;
public class Client {

    public void start(){
        try {
            ClientManager comunicador = new ClientManager("localhost", 3000);
            comunicador.start();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void sendRequest(){
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.start();
    }


}