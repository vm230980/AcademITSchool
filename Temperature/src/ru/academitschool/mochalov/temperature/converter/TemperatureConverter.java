package ru.academitschool.mochalov.temperature.converter;

import ru.academitschool.mochalov.temperature.scales.Scale;

public class TemperatureConverter {
    private final Scale[] scales;

    public TemperatureConverter(Scale[] scales) {
        this.scales = scales;
    }

    public double getConverted(double value, int fromTemperatureIndex, int toTemperatureIndex) {
        return scales[toTemperatureIndex].getConvertedFromCelsiusScale(scales[fromTemperatureIndex].getConvertedToCelsiusScale(value));
    }

    public Scale[] getScales() {
        return scales;
    }
}