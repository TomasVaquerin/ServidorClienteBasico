package org.example.server;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.UUID;

public class ClientHandler extends Thread {
    private final ClientHandlerManager comunicador;

    public ClientHandler(Socket socket) throws IOException {
        this.comunicador = new ClientHandlerManager(socket);
        System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
    }

    @Override
    public void run() {
        try {
            String clientInput;

            while (!(clientInput = comunicador.recibirEntrada()).equals("salir")) {
                switch (clientInput.toLowerCase()) {
                    case "5":
                        comunicador.enviarRespuesta("5");
                        break;
                    case "uuid":
                        comunicador.enviarRespuesta(UUID.randomUUID().toString());
                        break;
                    case "login":
                        comunicador.enviarRespuesta(" Prueba Cliente Servidor");
                        break;
                    default:
                        comunicador.enviarRespuesta(" No tengo ni idea");
                        break;
                }
            }

            comunicador.enviarRespuesta("Adios!");
            comunicador.cerrarStreams();
            comunicador.cerrarSocket();
            System.out.println("Cliente desconectado: " + comunicador.getClientSocket().getInetAddress().getHostAddress());

        } catch (IOException e) {
            System.out.println("Error al escuchar al cliente: " + e.getMessage());
        }
    }
}
