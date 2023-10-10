package org.example.client;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.cert.X509Certificate;

public class ClientManager {
    private SSLSocket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClientManager(String host, int port) throws IOException {
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) clientFactory.createSocket(host, port);
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    void start(){
        socket.setEnabledCipherSuites(new String[]{"TLS_AES_128_GCM_SHA256"});
        socket.setEnabledProtocols(new String[]{"TLSv1.3"});
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