package ru.academitschool.mochalov.temperature_gui;

import ru.academitschool.mochalov.temperature_model.TemperatureModel;
import ru.academitschool.mochalov.temperatures.Temperature;

import javax.swing.*;

import java.awt.*;

public class TemperatureGui {
    private final JFrame frame;

    public TemperatureGui(Temperature[] temperatures) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("КОНВЕРТЕР ТЕМПЕРАТУР");
        Image img = Toolkit.getDefaultToolkit().getImage("icon.png");

        int windowWidth = 400;
        int windowHeight = 230;

        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(screenSize.width / 2 - windowWidth / 2, screenSize.height / 2 - windowHeight / 2);

        frame.setIconImage(img);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        frame.add(box);

        JLabel title = new JLabel("КОНВЕРТЕР ТЕМПЕРАТУР", SwingConstants.CENTER);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        box.add(title);

        JLabel inputInviteText = new JLabel("Введите значение:");
        JLabel outputResultText = new JLabel("Результат:");

        JTextField inputField = new JTextField();
        inputField.setColumns(10);

        JTextField outputField = new JTextField(10);
        outputField.setEditable(false);

        JPanel iOTextPanel = new JPanel(new GridLayout(2, 2));
        iOTextPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        iOTextPanel.add(inputInviteText);
        iOTextPanel.add(inputField);
        iOTextPanel.add(outputResultText);
        iOTextPanel.add(outputField);

        box.add(Box.createVerticalStrut(5));
        box.add(iOTextPanel);

        int i = 0;
        String[] names = new String[temperatures.length];

        for (Temperature temperature : temperatures) {
            names[i] = temperature.getName();
            i++;
        }

        JPanel listsPanel = new JPanel(new GridLayout(1, 2));
        listsPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        JList<String> fromList = new JList<>(names);
        fromList.setBorder(BorderFactory.createRaisedBevelBorder());
        fromList.setSelectedIndex(0);

        JList<String> toList = new JList<>(names);
        toList.setBorder(BorderFactory.createRaisedBevelBorder());
        toList.setSelectedIndex(1);

        listsPanel.add(new JScrollPane(fromList));

        listsPanel.add(new JScrollPane(toList));
        box.add(listsPanel);

        box.add(Box.createVerticalStrut(10));

        JButton okButton = new JButton("Конвертировать!");
        okButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        box.add(okButton);
        box.add(Box.createVerticalStrut(5));

        okButton.addActionListener(a -> {
            if (inputField.getText().isEmpty()) {
                return;
            }

            if (!isNumeral(inputField.getText())) {
                JOptionPane.showMessageDialog(frame, "Неверный формат ввода. Введите число в формате %.%");
            } else {
                double degrees = Double.parseDouble(inputField.getText());
                outputField.setText(String.format("%.2f", TemperatureModel.getConverted(degrees, temperatures[fromList.getSelectedIndex()], temperatures[toList.getSelectedIndex()])));
            }
        });
    }

    private static boolean isNumeral(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        int commaCount = 0;
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '.') {
                commaCount++;
            }
            if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != '-') || commaCount > 1) {
                return false;
            }
        }

        return true;
    }

    public void show() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}