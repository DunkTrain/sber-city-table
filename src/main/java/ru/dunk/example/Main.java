package ru.dunk.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.dunk.example.City.*;

public class Main {

    public static void main(String[] args) {

        List<City> cities = readCitiesFromFile("cities.csv");
        printCities(cities);

    }
}

class City {

    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }

    protected static List<City> readCitiesFromFile(String fileName) {

        List<City> cities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length >= 6) {

                    City city = createCityFromData(parts);
                    cities.add(city);

                } else {
                    System.out.println("Некорректный формат данных для строки: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            e.printStackTrace();
        }

        return cities;
    }

    protected static City createCityFromData(String[] data) {

        String name = data[1].trim();
        String region = data[2].trim();
        String district = data[3].trim();
        int population = Integer.parseInt(data[4].trim());
        String foundation = data[5].trim();

        return new City(name, region, district, population, foundation);
    }

    protected static void printCities(List<City> cities) {

        for (City city : cities) {
            System.out.println(city);
        }

    }
}
