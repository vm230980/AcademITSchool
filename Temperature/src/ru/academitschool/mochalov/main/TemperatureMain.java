package ru.academitschool.mochalov.main;

import ru.academitschool.mochalov.temperature.converter.TemperatureConverter;
import ru.academitschool.mochalov.temperature.gui.TemperatureGui;
import ru.academitschool.mochalov.temperature.scales.CelsiusScale;
import ru.academitschool.mochalov.temperature.scales.FahrenheitScale;
import ru.academitschool.mochalov.temperature.scales.KelvinScale;
import ru.academitschool.mochalov.temperature.scales.Scale;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureGui gui = new TemperatureGui(new TemperatureConverter(new Scale[]{new CelsiusScale(), new FahrenheitScale(), new KelvinScale()}));
        gui.show();
    }
}