package ru.academitschool.mochalov.temperature.scales;

public class FahrenheitScale implements Scale {
    @Override
    public String getName() {
        return "Градусы Фаренгейта";
    }

    @Override
    public double getConvertedToCelsiusScale(double valueFahrenheit) {
        return (valueFahrenheit - 32) * 5 / 9;
    }

    @Override
    public double getConvertedFromCelsiusScale(double celsius) {
        return celsius * 1.8 + 32;
    }
}