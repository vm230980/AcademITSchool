package ru.academitschool.mochalov.temperature.converter;

import ru.academitschool.mochalov.temperature.scales.TemperatureScale;

public class TemperatureConverter {
    private final TemperatureScale[] scales;

    public TemperatureConverter(TemperatureScale[] scales) {
        this.scales = scales;
    }

    public double getConvertedTemperature(double temperature, int fromTemperatureScaleIndex, int toTemperatureScaleIndex) {
        return scales[toTemperatureScaleIndex].convertFromCelsiusScale(scales[fromTemperatureScaleIndex].convertToCelsiusScale(temperature));
    }

    public TemperatureScale[] getScales() {
        return scales;
    }
}