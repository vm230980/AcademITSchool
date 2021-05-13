package ru.academitschool.mochalov.temperature.main;

import ru.academitschool.mochalov.temperature.converter.TemperatureConverter;
import ru.academitschool.mochalov.temperature.gui.TemperatureGui;
import ru.academitschool.mochalov.temperature.scales.CelsiusScale;
import ru.academitschool.mochalov.temperature.scales.FahrenheitScale;
import ru.academitschool.mochalov.temperature.scales.KelvinScale;
import ru.academitschool.mochalov.temperature.scales.TemperatureScale;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureScale[] scales = new TemperatureScale[]{new CelsiusScale(), new FahrenheitScale(), new KelvinScale()};
        TemperatureGui gui = new TemperatureGui(new TemperatureConverter(scales));
        gui.show();
    }
}