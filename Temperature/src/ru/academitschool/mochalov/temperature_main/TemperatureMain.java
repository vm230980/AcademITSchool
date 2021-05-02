package ru.academitschool.mochalov.temperature_main;

import ru.academitschool.mochalov.temperature_gui.TemperatureGui;
import ru.academitschool.mochalov.temperatures.Celsius;
import ru.academitschool.mochalov.temperatures.Fahrenheit;
import ru.academitschool.mochalov.temperatures.Kelvin;
import ru.academitschool.mochalov.temperatures.Temperature;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureGui gui = new TemperatureGui(new Temperature[]{new Celsius(), new Fahrenheit(), new Kelvin()});
        gui.show();
    }
}