package org.example.models;

import java.util.List;

public class Clima {
    private List<Temperatura> temperaturas;

    public Clima() {
    }

    public List<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }
}
