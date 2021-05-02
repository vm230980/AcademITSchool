package ru.academitschool.mochalov.temperatures;

public class Fahrenheit implements Temperature{
    String name = "Градусы Фаренгейта";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCelsius(double valueFahrenheit) {
        return (valueFahrenheit - 32) * 5 / 9;
    }

    @Override
    public double getThis(double celsius) {
        return celsius * 1.8 + 32;
    }
}