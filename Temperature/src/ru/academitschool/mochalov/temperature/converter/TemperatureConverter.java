package ru.academitschool.mochalov.temperature.converter;

import ru.academitschool.mochalov.temperature.scales.TemperatureScale;

public class TemperatureConverter {
    private final TemperatureScale[] scales;

    public TemperatureConverter(TemperatureScale[] scales) {
        this.scales = scales;
    }

    public double getConverted(double value, int fromTemperatureIndex, int toTemperatureIndex) {
        return scales[toTemperatureIndex].getConvertedFromCelsiusScale(scales[fromTemperatureIndex].getConvertedToCelsiusScale(value));
    }

    public TemperatureScale[] getScales() {
        return scales;
    }
}