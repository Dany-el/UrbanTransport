package org.onpu.gui;

import org.onpu.entities.Driver;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class JOptionPaneDriver {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JLabel nameLabel = new JLabel("Name");
    private final JTextField nameTextField = new JTextField();
    private final JLabel surnameLabel = new JLabel("Surname");
    private final JTextField surnameTextField = new JTextField();
    private final JLabel patronymicLabel = new JLabel("Patronymic");
    private final JTextField patronymicTextField = new JTextField();
    private final JLabel phoneNumberLabel = new JLabel("Phone number (10-digits)");
    private final JTextField phoneNumberTextField = new JTextField(10);
    private final JLabel livingAddressLabel = new JLabel("Living address");
    private final JTextField livingAddressTextField = new JTextField();
    private final JLabel startOfCareerLabel = new JLabel("Start of career");
    private final JComboBox<String> dayListComboBox = new JComboBox<>();
    private final JComboBox<String> monthListComboBox = new JComboBox<>();
    private final JComboBox<String> yearListComboBox = new JComboBox<>();
    private final JLabel startOfRouteLabel = new JLabel("Start of the route");
    private final JComboBox<String> hoursOfRouteStartListComboBox = new JComboBox<>();
    private final JComboBox<String> minutesOfRouteStartListComboBox = new JComboBox<>();
    private final JLabel endOfRouteLabel = new JLabel("End of the route");
    private final JComboBox<String> hoursOfRouteEndListComboBox = new JComboBox<>();
    private final JComboBox<String> minutesOfRouteEndListComboBox = new JComboBox<>();
    private final JLabel nameOfRouteLabel = new JLabel("Name of the route");
    private final JTextField nameOfRouteTextField = new JTextField();
    private final JLabel driverIDLabel = new JLabel("Driver ID (6-digits)");
    private final JTextField driverIDTextField = new JTextField(6);

    private GridBagConstraints stackComponents(int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridwidth = 3;
        gbc.weightx = 0.0;
        gbc.gridx = 1;
        gbc.gridy = gridy;

        return gbc;
    }

    private GridBagConstraints stackComponents(int gridx, int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        return gbc;
    }

    /**
     * Configuring all ComboBox classes
     */
    private void listComboBoxConfiguring() {
        // d.m.y for the start of the career
        for (int num = 1; num <= 30; num++) {
            dayListComboBox.addItem("" + num);
        }

        for (int num = 1; num <= 12; num++) {
            monthListComboBox.addItem("" + num);
        }

        for (int num = 1989; num <= 2023; num++) {
            yearListComboBox.addItem("" + num);
        }

        // Hours and minutes for the start of the route
        for (int num = 0; num <= 23; num++) {
            hoursOfRouteStartListComboBox.addItem("" + num);
        }

        for (int num = 0; num < 60; num++) {
            minutesOfRouteStartListComboBox.addItem("" + num);
        }

        // Hours and minutes for the end of the route
        for (int num = 0; num <= 23; num++) {
            hoursOfRouteEndListComboBox.addItem("" + num);
        }

        for (int num = 0; num < 60; num++) {
            minutesOfRouteEndListComboBox.addItem("" + num);
        }
    }

    /**
     * Configuring all labels
     */
    private void labelsConfiguring() {
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        surnameLabel.setHorizontalAlignment(JLabel.CENTER);
        surnameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        patronymicLabel.setHorizontalAlignment(JLabel.CENTER);
        patronymicLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        phoneNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        livingAddressLabel.setHorizontalAlignment(JLabel.CENTER);
        livingAddressLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        startOfCareerLabel.setHorizontalAlignment(JLabel.CENTER);
        startOfCareerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        startOfRouteLabel.setHorizontalAlignment(JLabel.CENTER);
        startOfRouteLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        endOfRouteLabel.setHorizontalAlignment(JLabel.CENTER);
        endOfRouteLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        nameOfRouteLabel.setHorizontalAlignment(JLabel.CENTER);
        nameOfRouteLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        driverIDLabel.setHorizontalAlignment(JLabel.CENTER);
        driverIDLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    /**
     * Adding all components to the panel
     */
    private void panelAddingComponents() {
        panel.add(nameLabel, stackComponents(0));
        panel.add(nameTextField, stackComponents(1));

        panel.add(surnameLabel, stackComponents(2));
        panel.add(surnameTextField, stackComponents(3));

        panel.add(patronymicLabel, stackComponents(4));
        panel.add(patronymicTextField, stackComponents(5));

        panel.add(phoneNumberLabel, stackComponents(6));
        panel.add(phoneNumberTextField, stackComponents(7));

        panel.add(livingAddressLabel, stackComponents(8));
        panel.add(livingAddressTextField, stackComponents(9));

        panel.add(startOfCareerLabel, stackComponents(10));
        panel.add(dayListComboBox, stackComponents(1, 11));
        panel.add(monthListComboBox, stackComponents(2, 11));
        panel.add(yearListComboBox, stackComponents(3, 11));

        panel.add(startOfRouteLabel, stackComponents(12));
        panel.add(hoursOfRouteStartListComboBox, stackComponents(1, 13));
        panel.add(minutesOfRouteStartListComboBox, stackComponents(3, 13));

        panel.add(endOfRouteLabel, stackComponents(14));
        panel.add(hoursOfRouteEndListComboBox, stackComponents(1, 15));
        panel.add(minutesOfRouteEndListComboBox, stackComponents(3, 15));

        panel.add(nameOfRouteLabel, stackComponents(16));
        panel.add(nameOfRouteTextField, stackComponents(17));

        panel.add(driverIDLabel, stackComponents(18));
        panel.add(driverIDTextField, stackComponents(19));
    }

    /**
     * Sets all text fields and combo boxes to zero-value
     */
    private void clearAll() {
        nameTextField.setText("");
        surnameTextField.setText("");
        patronymicTextField.setText("");
        phoneNumberTextField.setText("");
        livingAddressTextField.setText("");
        yearListComboBox.setSelectedIndex(0);
        monthListComboBox.setSelectedIndex(0);
        dayListComboBox.setSelectedIndex(0);
        hoursOfRouteStartListComboBox.setSelectedIndex(0);
        minutesOfRouteStartListComboBox.setSelectedIndex(0);
        hoursOfRouteEndListComboBox.setSelectedIndex(0);
        minutesOfRouteEndListComboBox.setSelectedIndex(0);
        nameOfRouteTextField.setText("");
        driverIDTextField.setText("");
    }

    /**
     * Groups up all configuration
     *
     * @return Configured panel with configured components
     */
    private JPanel panelWithConfiguredComponents() {
        labelsConfiguring();
        listComboBoxConfiguring();
        panelAddingComponents();
        return panel;
    }

    public Driver createDriver(JFrame frame) throws Exception {
        int choice = JOptionPane.showConfirmDialog(null,
                panelWithConfiguredComponents(),
                "Driver creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {

            if (driverIDTextField.getText().length() > 6) {
                JOptionPane.showMessageDialog(frame, "ID is not short enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (driverIDTextField.getText().length() < 6) {
                JOptionPane.showMessageDialog(frame, "ID is not long enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (phoneNumberTextField.getText().length() > 10) {
                JOptionPane.showMessageDialog(frame, "Phone number is not short enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (phoneNumberTextField.getText().length() < 10) {
                JOptionPane.showMessageDialog(frame, "Phone number is not long enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int yearStartOfCareer = Integer.parseInt(yearListComboBox.getItemAt(yearListComboBox.getSelectedIndex()));
                int monthStartOfCareer = Integer.parseInt(monthListComboBox.getItemAt(monthListComboBox.getSelectedIndex()));
                int dayStartOfCareer = Integer.parseInt(dayListComboBox.getItemAt(dayListComboBox.getSelectedIndex()));

                int hoursStartOfRoute = Integer.parseInt(hoursOfRouteStartListComboBox.getItemAt(
                        hoursOfRouteStartListComboBox.getSelectedIndex())
                );

                int minutesStartOfRoute = Integer.parseInt(minutesOfRouteStartListComboBox.getItemAt(
                        minutesOfRouteStartListComboBox.getSelectedIndex())
                );

                int hoursEndOfRoute = Integer.parseInt(hoursOfRouteEndListComboBox.getItemAt(
                        hoursOfRouteEndListComboBox.getSelectedIndex())
                );

                int minutesEndOfRoute = Integer.parseInt(minutesOfRouteEndListComboBox.getItemAt(
                        minutesOfRouteEndListComboBox.getSelectedIndex())
                );

                Driver d = new Driver(
                        nameTextField.getText(),
                        surnameTextField.getText(),
                        patronymicTextField.getText(),
                        phoneNumberTextField.getText(),
                        livingAddressTextField.getText(),
                        LocalDate.of(yearStartOfCareer, monthStartOfCareer, dayStartOfCareer),
                        LocalTime.of(hoursStartOfRoute, minutesStartOfRoute),
                        LocalTime.of(hoursEndOfRoute, minutesEndOfRoute),
                        nameOfRouteTextField.getText(),
                        driverIDTextField.getText()
                );
                clearAll();
                return d;
            }
        }
        return null;
    }

    private JPanel setDriverDataToTextFields(Driver driver) {
        labelsConfiguring();
        listComboBoxConfiguring();

        nameTextField.setText(driver.getName());
        surnameTextField.setText(driver.getSurname());
        patronymicTextField.setText(driver.getPatronymic());
        phoneNumberTextField.setText(driver.getPhoneNumber());
        livingAddressTextField.setText(driver.getLivingAddress());
        yearListComboBox.setSelectedIndex(
                driver.getStartOfCareer().getYear() - Integer.parseInt(yearListComboBox.getItemAt(0))
        );
        monthListComboBox.setSelectedIndex(driver.getStartOfCareer().getMonthValue() - 1);
        dayListComboBox.setSelectedIndex(driver.getStartOfCareer().getDayOfMonth() - 1);
        hoursOfRouteStartListComboBox.setSelectedIndex(driver.getStartOfRoute().getHour());
        minutesOfRouteStartListComboBox.setSelectedIndex(driver.getStartOfRoute().getMinute());
        hoursOfRouteEndListComboBox.setSelectedIndex(driver.getEndOfRoute().getHour());
        minutesOfRouteEndListComboBox.setSelectedIndex(driver.getEndOfRoute().getMinute());
        nameOfRouteTextField.setText(driver.getRouteName());
        driverIDTextField.setText(driver.getId());
        driverIDTextField.setEnabled(false);

        panelAddingComponents();
        return panel;
    }

    public Driver editDriver(JFrame frame, Driver driver) {

        int choice = JOptionPane.showConfirmDialog(null,
                setDriverDataToTextFields(driver),
                "Driver editing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            if (driverIDTextField.getText().length() > 6) {
                JOptionPane.showMessageDialog(frame,
                        "ID is not short enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (driverIDTextField.getText().length() < 6) {
                JOptionPane.showMessageDialog(frame,
                        "ID is not long enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (phoneNumberTextField.getText().length() > 10) {
                JOptionPane.showMessageDialog(frame,
                        "Phone number is not short enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (phoneNumberTextField.getText().length() < 10) {
                JOptionPane.showMessageDialog(frame,
                        "Phone number is not long enough", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int yearStartOfCareer = Integer.parseInt(
                        yearListComboBox.getItemAt(yearListComboBox.getSelectedIndex())
                );
                int monthStartOfCareer = Integer.parseInt(
                        monthListComboBox.getItemAt(monthListComboBox.getSelectedIndex())
                );
                int dayStartOfCareer = Integer.parseInt(
                        dayListComboBox.getItemAt(dayListComboBox.getSelectedIndex())
                );

                int hoursStartOfRoute = Integer.parseInt(hoursOfRouteStartListComboBox.getItemAt(
                        hoursOfRouteStartListComboBox.getSelectedIndex())
                );

                int minutesStartOfRoute = Integer.parseInt(minutesOfRouteStartListComboBox.getItemAt(
                        minutesOfRouteStartListComboBox.getSelectedIndex())
                );

                int hoursEndOfRoute = Integer.parseInt(hoursOfRouteEndListComboBox.getItemAt(
                        hoursOfRouteEndListComboBox.getSelectedIndex())
                );

                int minutesEndOfRoute = Integer.parseInt(minutesOfRouteEndListComboBox.getItemAt(
                        minutesOfRouteEndListComboBox.getSelectedIndex())
                );

                driver.setName(nameTextField.getText());
                driver.setSurname(surnameTextField.getText());
                driver.setPatronymic(patronymicTextField.getText());
                try {
                    driver.setPhoneNumber(phoneNumberTextField.getText());
                    driver.setId(driverIDTextField.getText());
                } catch (Exception ignored) {
                }
                driver.setLivingAddress(livingAddressTextField.getText());
                driver.setStartOfCareer(LocalDate.of(yearStartOfCareer, monthStartOfCareer, dayStartOfCareer));
                driver.setStartOfRoute(LocalTime.of(hoursStartOfRoute, minutesStartOfRoute));
                driver.setEndOfRoute(LocalTime.of(hoursEndOfRoute, minutesEndOfRoute));
                driver.setRouteName(nameOfRouteTextField.getText());
            }
        }
        return driver;
    }
}
