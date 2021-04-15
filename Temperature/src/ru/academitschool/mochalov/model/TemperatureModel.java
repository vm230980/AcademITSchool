package ru.academitschool.mochalov.model;

public class TemperatureModel {
    public static double getConverted(double degrees, String option) {
        return switch (option) {
            case "cToK" -> degrees + 273.15;
            case "fToC" -> (degrees - 32) * 5 / 9;
            case "fToK" -> (degrees - 32) * 5 / 9 + 273.15;
            case "kToC" -> degrees - 273.15;
            case "kToF" -> (degrees - 273.15) * 9 / 5 + 32;
            default -> degrees * 1.8 + 32;
        };
    }
}