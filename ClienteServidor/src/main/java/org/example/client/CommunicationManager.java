package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationManager {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public CommunicationManager(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String receiveResponse() throws IOException {
        return reader.readLine();
    }

    public void closeConnection() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}