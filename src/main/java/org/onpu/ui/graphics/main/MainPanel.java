package org.onpu.ui.graphics.main;


import org.onpu.ui.action_listener.ButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JLabel mainLabel = new JLabel("Text will be shown here");
    private JButton getAverageDriverWorkingTimeButton = new JButton("Avg working time");
    private JButton getAverageLengthOfServiceButton = new JButton("Avg length of service");
    private JButton getDriverWithGreatLengthOfServiceButton = new JButton("Greater length of service");
    private JButton getDriversOfSpecificRouteButton = new JButton("Drivers of the given route");
    private JButton getCountOfTransportsAtTimeButton = new JButton("Count of transport at given time");
    private JButton getGroupOfDriversButton = new JButton("Get all IDs of drivers");
    private JButton settingsButton = new JButton("Settings");

    public MainPanel() {
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(Color.decode("#d6f3fa"));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        getAverageDriverWorkingTimeButton.addActionListener(ButtonActionListener.Requests.getAverageDriverWorkingTimeActionListener(this, mainLabel));
        getAverageLengthOfServiceButton.addActionListener(ButtonActionListener.Requests.getAverageLengthOfServiceActionListener(this, mainLabel));
        getDriverWithGreatLengthOfServiceButton.addActionListener(ButtonActionListener.Requests.getDriverWithGreatLengthOfServiceActionListener(this, mainLabel));
        getDriversOfSpecificRouteButton.addActionListener(ButtonActionListener.Requests.getDriversOfSpecificRouteActionListener(this, mainLabel));
        getCountOfTransportsAtTimeButton.addActionListener(ButtonActionListener.Requests.getCountOfTransportsAtTimeActionListener(mainLabel));
        getGroupOfDriversButton.addActionListener(ButtonActionListener.Requests.getDriversActionListener(this, mainLabel));
        settingsButton.addActionListener(ButtonActionListener.getSettingsActionListener());

        this.add(mainLabel);
        this.add(getAverageDriverWorkingTimeButton);
        this.add(getAverageLengthOfServiceButton);
        this.add(getDriverWithGreatLengthOfServiceButton);
        this.add(getDriversOfSpecificRouteButton);
        this.add(getCountOfTransportsAtTimeButton);
        this.add(getGroupOfDriversButton);
        this.add(settingsButton);
    }
}
