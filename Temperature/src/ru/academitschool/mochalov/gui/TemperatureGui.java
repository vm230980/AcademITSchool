package ru.academitschool.mochalov.gui;

import ru.academitschool.mochalov.model.TemperatureModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.text.NumberFormat;

public class TemperatureGui {
    public static void view() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Image img = Toolkit.getDefaultToolkit().getImage("icon.png");

        JFrame frame = new JFrame("КОНВЕРТЕР ТЕМПЕРАТУР");
        int windowWidth = 400;
        int windowHeight = 370;

        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        frame.setVisible(true);

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

        JFormattedTextField inputField = new JFormattedTextField(NumberFormat.getNumberInstance());
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

        String cToFButtonString = "° Цельсия - ° Фаренгейта";
        JRadioButton cToFButton = new JRadioButton(cToFButtonString, true);
        cToFButton.setActionCommand("cToF");

        String cToKButtonString = "° Цельсия - ° Кельвина";
        JRadioButton cToKButton = new JRadioButton(cToKButtonString);
        cToKButton.setActionCommand("cToK");

        String fToCButtonString = "° Фаренгейта - ° Цельсия";
        JRadioButton fToCButton = new JRadioButton(fToCButtonString);
        fToCButton.setActionCommand("fToC");

        String fToKButtonString = "° Фаренгейта - ° Кельвина";
        JRadioButton fToKButton = new JRadioButton(fToKButtonString);
        fToKButton.setActionCommand("fToK");

        String kToCButtonString = "° Кельвина - ° Цельсия";
        JRadioButton kToCButton = new JRadioButton(kToCButtonString);
        kToCButton.setActionCommand("kToC");

        String kToFButtonString = "° Кельвина - ° Фаренгейта";
        JRadioButton kToFButton = new JRadioButton(kToFButtonString);
        kToFButton.setActionCommand("kToF");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(cToFButton);
        buttonGroup.add(cToKButton);
        buttonGroup.add(fToCButton);
        buttonGroup.add(fToKButton);
        buttonGroup.add(kToCButton);
        buttonGroup.add(kToFButton);

        JPanel radioButtonsPanel = new JPanel(new GridBagLayout());
        radioButtonsPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 5, 10);

        c.gridx = 0;
        c.gridy = 0;
        radioButtonsPanel.add(cToFButton, c);

        c.gridx = 1;
        c.gridy = 0;
        radioButtonsPanel.add(cToKButton, c);

        c.gridx = 0;
        c.gridy = 1;
        radioButtonsPanel.add(fToCButton, c);

        c.gridx = 1;
        c.gridy = 1;
        radioButtonsPanel.add(fToKButton, c);

        c.gridx = 0;
        c.gridy = 2;
        radioButtonsPanel.add(kToCButton, c);

        c.gridx = 1;
        c.gridy = 2;
        radioButtonsPanel.add(kToFButton, c);

        box.add(radioButtonsPanel);

        JLabel instructions = new JLabel("<html>1. Введите значение температуры в поле слева.<br />2. Выберите ниже параметры перевода и нажмите<br />кнопку перевести.<br />3. В поле справа будет полученный результат<br /><tr></tr></html>", SwingConstants.CENTER);
        instructions.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        TitledBorder titleBorder;
        titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Инструкции");
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        instructions.setBorder(titleBorder);

        box.add(instructions);
        box.add(Box.createVerticalStrut(10));

        JButton okButton = new JButton("Конвертировать!");
        okButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        box.add(okButton);
        box.add(Box.createVerticalStrut(30));

        okButton.addActionListener(e -> {
            if (inputField.getText().equals("")) {
                return;
            }

            double degrees = ((Number) inputField.getValue()).doubleValue();

            outputField.setText(String.format("%.2f", TemperatureModel.getConverted(degrees, buttonGroup.getSelection().getActionCommand())));
        });
    }
}