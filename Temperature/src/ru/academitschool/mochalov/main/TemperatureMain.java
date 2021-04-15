package ru.academitschool.mochalov.main;

import ru.academitschool.mochalov.gui.TemperatureGui;

import javax.swing.*;

public class TemperatureMain {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(TemperatureGui::view);
    }
}