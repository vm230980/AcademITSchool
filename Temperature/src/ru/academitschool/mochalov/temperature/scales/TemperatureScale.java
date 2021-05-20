package ru.academitschool.mochalov.temperature.scales;

public interface TemperatureScale {
    String getName();

    double convertToCelsiusScale(double temperature);

    double convertFromCelsiusScale(double temperature);
}