package ru.academitschool.mochalov.temperature.scales;

public interface TemperatureScale {
    String getName();

    double getConvertedToCelsiusScale(double valueThis);

    double getConvertedFromCelsiusScale(double celsius);
}