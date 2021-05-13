package ru.academitschool.mochalov.temperature.scales;

public class CelsiusScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Градусы Цельсия";
    }

    @Override
    public double getConvertedToCelsiusScale(double valueCelsius) {
        return valueCelsius;
    }

    @Override
    public double getConvertedFromCelsiusScale(double celsius) {
        return celsius;
    }
}