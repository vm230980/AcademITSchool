package ru.academitschool.mochalov.temperature.scales;

public class KelvinScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Градусы Кельвина";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature + 273.15;
    }
}