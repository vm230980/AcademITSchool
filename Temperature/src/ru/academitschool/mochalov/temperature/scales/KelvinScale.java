package ru.academitschool.mochalov.temperature.scales;

public class KelvinScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Градусы Кельвина";
    }

    @Override
    public double getConvertedToCelsiusScale(double valueKelvin) {
        return valueKelvin - 273.15;
    }

    @Override
    public double getConvertedFromCelsiusScale(double celsius) {
        return celsius + 273.15;
    }
}