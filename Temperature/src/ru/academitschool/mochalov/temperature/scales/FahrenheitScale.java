package ru.academitschool.mochalov.temperature.scales;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Градусы Фаренгейта";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature * 1.8 + 32;
    }
}