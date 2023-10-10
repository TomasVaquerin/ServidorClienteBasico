package org.example;

import org.example.servives.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();
        try {
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}