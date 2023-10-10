package org.example.server;

import java.io.*;
import java.net.Socket;

public class ClientHandlerManager {
    private final Socket clientSocket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientHandlerManager(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    public void enviarRespuesta(String respuesta) {
        out.println(respuesta);
    }

    public String recibirEntrada() throws IOException {
        return in.readLine();
    }

    public void cerrarStreams() throws IOException {
        in.close();
        out.close();
    }

    public void cerrarSocket() throws IOException {
        clientSocket.close();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
