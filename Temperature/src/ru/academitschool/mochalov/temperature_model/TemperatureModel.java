package ru.academitschool.mochalov.temperature_model;

import ru.academitschool.mochalov.temperatures.Temperature;

public class TemperatureModel {
    public static double getConverted(double value, Temperature fromTemperature, Temperature toTemperature) {
        return toTemperature.getThis(fromTemperature.getCelsius(value));
    }
}