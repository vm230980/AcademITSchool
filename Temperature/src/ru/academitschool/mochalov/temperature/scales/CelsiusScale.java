package ru.academitschool.mochalov.temperature.scales;

public class CelsiusScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Градусы Цельсия";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature;
    }
}