package ru.academitschool.mochalov.temperatures;

public class Celsius implements Temperature{
    String name = "Градусы Цельсия";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCelsius(double valueCelsius) {
        return valueCelsius;
    }

    @Override
    public double getThis(double celsius) {
        return celsius;
    }
}