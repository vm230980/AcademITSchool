package ru.academitschool.mochalov.temperature.scales;

public interface Scale {
    String getName();

    double getConvertedToCelsiusScale(double valueThis);

    double getConvertedFromCelsiusScale(double celsius);
}