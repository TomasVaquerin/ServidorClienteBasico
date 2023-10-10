package org.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Temperatura {
    private String localidad;
    private String provincia;
    private double tempMax;
    private LocalTime hsTempMax;
    private double tempMin;
    private LocalTime hsTempMin;
    private double precipitacion;

    private LocalDate dia;

    public Temperatura(String localidad, String provincia, double tempMax, LocalTime hsTempMax, double tempMin, LocalTime hsTempMin, double precipitacion, LocalDate dia) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.tempMax = tempMax;
        this.hsTempMax = hsTempMax;
        this.tempMin = tempMin;
        this.hsTempMin = hsTempMin;
        this.precipitacion = precipitacion;
        this.dia = dia;
    }

    public Temperatura() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public LocalTime getHsTempMax() {
        return hsTempMax;
    }

    public void setHsTempMax(LocalTime hsTempMax) {
        this.hsTempMax = hsTempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public LocalTime getHsTempMin() {
        return hsTempMin;
    }

    public void setHsTempMin(LocalTime hsTempMin) {
        this.hsTempMin = hsTempMin;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(double precipitacion) {
        this.precipitacion = precipitacion;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", tempMax=" + tempMax +
                ", hsTempMax=" + hsTempMax +
                ", tempMin=" + tempMin +
                ", hsTempMin=" + hsTempMin +
                ", precipitacion=" + precipitacion +
                ", dia=" + dia +
                '}';
    }
}
