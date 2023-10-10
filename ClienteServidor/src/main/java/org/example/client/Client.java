package org.example.client;

import java.io.IOException;
public class Client {
    public static void main(String[] args) {
        try {
            CommunicationManager comunicador = new CommunicationManager("localhost", 3000);

            String[] mensajes = {"login", "fecha", "uuid", "otro", "salir"};

            // Vamos a mandar todos los mensajes
            for (String mensaje : mensajes) {
                comunicador.sendMessage(mensaje);
                String response = comunicador.receiveResponse();
                System.out.println("respuesta del servidor" + response);
                Thread.sleep(2000);
            }

            comunicador.closeConnection();
        } catch (InterruptedException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}