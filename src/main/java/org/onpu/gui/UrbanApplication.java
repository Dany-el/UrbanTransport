package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;

public final class UrbanApplication extends JFrame {
    private static final JPanel mainPanel = new JPanel();
    private static final JLabel mainText = new JLabel("Text will be shown here");
    private static final JButton getAverageDriverWorkingTimeButton = new JButton("Avg working time");
    private static final JButton getAverageLengthOfServiceButton = new JButton("Avg length of service");
    private static final JButton getDriverWithGreatLengthOfServiceButton = new JButton("Greater length of service");
    private static final JButton getDriversOfSpecificRouteButton = new JButton("Drivers of the given route");
    private static final JButton getCountOfTransportsAtTimeButton = new JButton("Count of transport at given time");
    private static final JButton getGroupOfDriversButton = new JButton("Get all IDs of drivers");
    private static final JButton settingsButton = new JButton("Settings");
    private static SettingsWindow sw;
    private static final GridBagConstraints gbc = new GridBagConstraints();
    private static final UrbanCompany urbanCompany = new UrbanCompany("Urban Company");

    public UrbanApplication() {
        mainTextConfiguration();
        buttonsConfigure();
        panelConfiguration();
    }

    /**
     * Configuration of mainText
     */
    private void mainTextConfiguration() {
        mainText.setHorizontalAlignment(JLabel.CENTER);
        mainText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
    }

    /**
     * Button configurations
     */
    private void buttonsConfigure() {
        settingsButtonConfiguration();
        getDriversOfSpecificRouteButtonConfiguration();
        getAverageDriverWorkingTimeButtonConfiguration();
        getAverageLengthOfServiceButtonConfiguration();
        getCountOfTransportsAtTimeButtonConfiguration();
        getDriverWithGreatLengthOfServiceButtonConfiguration();
        getGroupOfDriversButtonConfiguration();
    }

    /**
     * Adds ActionListener for settingsButton
     */
    private void settingsButtonConfiguration() {
        settingsButton.addActionListener(e -> {
            if (sw == null) {
                sw = new SettingsWindow();
                sw.setUrbanCompany(urbanCompany);
                sw.start();
            } else if (!sw.isOpened()) {
                sw.start();
            }
        });
    }

    private void getAverageDriverWorkingTimeButtonConfiguration() {
        getAverageDriverWorkingTimeButton.addActionListener(e -> {
            double avgWorkingTime = urbanCompany.getAverageDriverWorkingTime();
            if (avgWorkingTime > 0) {
                mainText.setText("Avg Time: " + avgWorkingTime);
            } else {
                JOptionPane.showMessageDialog(this, "The list with drivers is empty",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void getAverageLengthOfServiceButtonConfiguration() {
        getAverageLengthOfServiceButton.addActionListener(e -> {
            double avgLengthOfService = urbanCompany.getAverageLengthOfService();
            if (avgLengthOfService > 0) {
                mainText.setText("Avg Years: " + avgLengthOfService);
            } else {
                JOptionPane.showMessageDialog(this, "The list with drivers is empty",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void getDriverWithGreatLengthOfServiceButtonConfiguration() {
        getDriverWithGreatLengthOfServiceButton.addActionListener(e -> {
            Driver driver = null;
            try {
                driver = urbanCompany.getDriverWithGreatLengthOfService();
            } catch (Exception exception) {
                exception.printStackTrace(System.out);
                JOptionPane.showMessageDialog(this, "The list with drivers is empty",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } finally {
                if (driver != null) {
                    mainText.setText("ID " + driver.getId());
                }
            }
        });
    }

    private void getDriversOfSpecificRouteButtonConfiguration() {
        getDriversOfSpecificRouteButton.addActionListener(e -> {
            String routeName = JOptionPane.showInputDialog(this, "Route name");
            if (routeName != null) {
                List<Driver> listOfDrivers = urbanCompany.getDriversOfSpecificRoute(routeName);
                if (!listOfDrivers.isEmpty()) {
                    mainText.setText("Count: " + listOfDrivers.size());
                    String listOfDriversID = "";
                    for (Driver d :
                            listOfDrivers) {
                        listOfDriversID += "#" + d.getId() + "\n";
                    }
                    JOptionPane.showMessageDialog(this, listOfDriversID,
                            "IDs", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Not found",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void getCountOfTransportsAtTimeButtonConfiguration() {
        getCountOfTransportsAtTimeButton.addActionListener(e -> {
            JComboBox<String> hoursComboBox = new JComboBox<>();
            for (int i = 1; i <= 23; i++) {
                hoursComboBox.addItem("" + i);
            }

            JComboBox<String> minutesComboBox = new JComboBox<>();
            for (int i = 1; i < 60; i++) {
                minutesComboBox.addItem("" + i);
            }

            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(hoursComboBox, stackComponents(1, 0));
            panel.add(minutesComboBox, stackComponents(2, 0));

            int choice = JOptionPane.showConfirmDialog(null,
                    panel,
                    "Hours & minutes",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (choice == JOptionPane.OK_OPTION) {
                int hours = Integer.parseInt(hoursComboBox.getItemAt(hoursComboBox.getSelectedIndex()));
                int minutes = Integer.parseInt(minutesComboBox.getItemAt(minutesComboBox.getSelectedIndex()));
                long count = urbanCompany.getCountOfTransportsAtTime(LocalTime.of(hours, minutes));
                mainText.setText("Count: " + count);
            }
        });
    }

    private void getGroupOfDriversButtonConfiguration() {
        getGroupOfDriversButton.addActionListener(e -> {
            List<Driver> listOfDrivers = urbanCompany.getGroupOfDrivers();
            mainText.setText("Count: " + listOfDrivers.size());
            if (!listOfDrivers.isEmpty()) {
                String listOfDriversID = "";
                for (Driver d :
                        listOfDrivers) {
                    listOfDriversID += "#" + d.getId() + "\n";
                }
                JOptionPane.showMessageDialog(this, listOfDriversID,
                        "IDs", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Configuration of mainPanel
     */
    private void panelConfiguration() {
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#d6f3fa"));
        mainPanel.add(mainText, stackComponents(0));
        mainPanel.add(getAverageDriverWorkingTimeButton, stackComponents(1));
        mainPanel.add(getAverageLengthOfServiceButton, stackComponents(2));
        mainPanel.add(getDriverWithGreatLengthOfServiceButton, stackComponents(3));
        mainPanel.add(getDriversOfSpecificRouteButton, stackComponents(4));
        mainPanel.add(getCountOfTransportsAtTimeButton, stackComponents(5));
        mainPanel.add(getGroupOfDriversButton, stackComponents(6));
        mainPanel.add(settingsButton, stackComponents(7));
    }
    /**
     * Stacks components at y coordinate
     *
     * @param gridy place at y-grid
     * @return configured GridBagConstraints object
     */
    private GridBagConstraints stackComponents(int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        gbc.gridy = gridy;

        return gbc;
    }

    /**
     * Stacks components at x and y coordinates
     *
     * @param gridx place at x coordinate
     * @param gridy place at y coordinate
     * @return configured GridBagConstraints object
     */
    private GridBagConstraints stackComponents(int gridx, int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.weightx = 1.0;
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        return gbc;
    }

    /**
     * Starts the program
     */
    public void start() {
        this.setTitle("Urban Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.add(mainPanel);
        this.setResizable(false);
        this.setVisible(true);
    }
}
