package org.example.controller;

import org.example.models.Clima;
import org.example.models.Temperatura;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoadFile<T> {

    private final String nameFolder = "data";
    private final String[] nameFiles = {"Aemet20171029.csv", "Aemet20171030.csv", "Aemet20171031.csv"};

    private Path currentRelativePath = Paths.get("");
    private String ruta = currentRelativePath.toAbsolutePath().toString();
    private String dir = ruta + File.separator + nameFolder;

    private static LoadFile instance;
    private List<Temperatura> elementosGrupos;

    private Clima climas;

    private LoadFile() {
        if (instance == null) {
            instance = this;
        }
    }

    public static LoadFile getInstance() {
        if (instance == null) {
            instance = new LoadFile();
        }
        return instance;
    }

    /*
    public void leer() {
        climas = new Clima();
        for (String fileName : nameFiles) {
            elementosGrupos = new ArrayList<>();

            String filePath = dir + File.separator + fileName;
            try (BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
                Stream<T> elementos = (Stream<T>) lector.lines().skip(1)
                        .map(this::getElemento);
                elementos.forEach(System.out::println);
                elementosGrupos.add(this.getElemento(lector.readLine()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            climas.setTemperaturas(elementosGrupos);
        }
    }

     */
    public void leer() {
        climas = new Clima();

        for (String fileName : nameFiles) {
            elementosGrupos = new ArrayList<>();

            String filePath = dir + File.separator + fileName;
            try (BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
                lector.readLine();

                String linea;
                while ((linea = lector.readLine()) != null) {
                    Temperatura temperatura = this.getElemento(linea, fileName);
                    elementosGrupos.add(temperatura);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            climas.setTemperaturas(elementosGrupos);
        }
    }

    private Temperatura getElemento(String linea, String filename) {
        String[] datos = linea.split(";");
        String localidad = datos[0];
        String provincia = datos[1];
        double tempMax = Double.parseDouble(datos[2]);
        LocalTime hsTempMax = parseTimeWithPatterns(datos[3]);
        double tempMin = Double.parseDouble(datos[4]);
        LocalTime hsTempMin = parseTimeWithPatterns(datos[5]);
        double precipitacion = Double.parseDouble(datos[6]);
        LocalDate dia = parseNameFileDate(filename);

        Temperatura t = new Temperatura(localidad, provincia, tempMax, hsTempMax, tempMin, hsTempMin, precipitacion, dia);

        System.out.println(t);

        return t;
    }

    private LocalTime parseTimeWithPatterns(String timeStr) {
        List<String> patterns = new ArrayList<>(Arrays.asList("H:mm", "h:mm a"));
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalTime.parse(timeStr, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next pattern if parsing fails
            }
        }
        return null; // Return null if parsing with all patterns fails
    }

    private LocalDate parseNameFileDate(String fileName) {
        String dateString = fileName.substring(fileName.length() - 12, fileName.length() - 4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        return LocalDate.parse(dateString, formatter);
    }


    public static void main(String[] args) {
        LoadFile<Temperatura> loadFile = new LoadFile<>();
        loadFile.leer();
    }
}

