package ru.academitschool.mochalov.temperatures;

public class Kelvin implements Temperature {
    String name = "Градусы Кельвина";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCelsius(double valueKelvin) {
        return valueKelvin - 273.15;
    }

    @Override
    public double getThis(double celsius) {
        return celsius + 273.15;
    }
}