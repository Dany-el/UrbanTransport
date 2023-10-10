package org.onpu.gui;

import org.onpu.entities.Driver;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public final class JOptionPaneDriver {
    private static final GridBagConstraints gbc = new GridBagConstraints();
    private static final JPanel panel = new JPanel(new GridBagLayout());
    private static final JLabel nameLabel = new JLabel("Name");
    private static final JTextField nameTextField = new JTextField();
    private static final JLabel surnameLabel = new JLabel("Surname");
    private static final JTextField surnameTextField = new JTextField();
    private static final JLabel patronymicLabel = new JLabel("Patronymic");
    private static final JTextField patronymicTextField = new JTextField();
    private static final JLabel phoneNumberLabel = new JLabel("Phone number (10-digits)");
    private static final JTextField phoneNumberTextField = new JTextField(10);
    private static final JLabel livingAddressLabel = new JLabel("Living address");
    private static final JTextField livingAddressTextField = new JTextField();
    private static final JLabel startOfCareerLabel = new JLabel("Start of career");
    private static final JComboBox<String> dayListComboBox = new JComboBox<>();
    private static final JComboBox<String> monthListComboBox = new JComboBox<>();
    private static final JComboBox<String> yearListComboBox = new JComboBox<>();
    private static final JLabel startOfRouteLabel = new JLabel("Start of the route");
    private static final JComboBox<String> hoursOfRouteStartListComboBox = new JComboBox<>();
    private static final JComboBox<String> minutesOfRouteStartListComboBox = new JComboBox<>();
    private static final JLabel endOfRouteLabel = new JLabel("End of the route");
    private static final JComboBox<String> hoursOfRouteEndListComboBox = new JComboBox<>();
    private static final JComboBox<String> minutesOfRouteEndListComboBox = new JComboBox<>();
    private static final JLabel nameOfRouteLabel = new JLabel("Name of the route");
    private static final JTextField nameOfRouteTextField = new JTextField();
    private static final JLabel driverIDLabel = new JLabel("Driver ID (6-digits)");
    private static final JTextField driverIDTextField = new JTextField(6);
    private static final List<JLabel> labels = Arrays.asList(
            nameLabel, surnameLabel, patronymicLabel,
            phoneNumberLabel, livingAddressLabel,
            startOfCareerLabel, startOfRouteLabel, endOfRouteLabel, nameOfRouteLabel,
            driverIDLabel
    );

    /**
     * Stacks components at y coordinate
     *
     * @param gridy place at y-grid
     * @return configured GridBagConstraints object
     */
    private GridBagConstraints stackComponents(int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridwidth = 3;
        gbc.weightx = 0.0;
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
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        return gbc;
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
     * Parse String values from Date-based ComboBoxes to int type
     *
     * @return new immutable LocalDate class with parsed to int values from ComboBox components
     */
    private LocalDate getDateOfStartOfCareerFromComboBox() {
        int year = Integer.parseInt(
                yearListComboBox.getItemAt(yearListComboBox.getSelectedIndex())
        );
        int month = Integer.parseInt(
                monthListComboBox.getItemAt(monthListComboBox.getSelectedIndex())
        );
        int day = Integer.parseInt(
                dayListComboBox.getItemAt(dayListComboBox.getSelectedIndex())
        );
        return LocalDate.of(year, month, day);
    }

    /**
     * Parse String values from TimeStart-based ComboBoxes to int type
     *
     * @return new immutable LocalTime class with parsed to int values from ComboBox components
     */
    private LocalTime getTimeOfStartOfRouteFromComboBox() {
        int hours = Integer.parseInt(hoursOfRouteStartListComboBox.getItemAt(
                hoursOfRouteStartListComboBox.getSelectedIndex())
        );

        int minutes = Integer.parseInt(minutesOfRouteStartListComboBox.getItemAt(
                minutesOfRouteStartListComboBox.getSelectedIndex())
        );
        return LocalTime.of(hours, minutes);
    }

    /**
     * Parse String values from TimeEnd-based ComboBoxes to int type
     *
     * @return new immutable LocalTime class with parsed to int values from ComboBox components
     */
    private LocalTime getTimeOfEndOfRouteFromComboBox() {
        int hours = Integer.parseInt(hoursOfRouteEndListComboBox.getItemAt(
                hoursOfRouteEndListComboBox.getSelectedIndex())
        );

        int minutes = Integer.parseInt(minutesOfRouteEndListComboBox.getItemAt(
                minutesOfRouteEndListComboBox.getSelectedIndex())
        );
        return LocalTime.of(hours, minutes);
    }

    /**
     * Clears all items in ComboBoxes and adds the same one
     */
    private void listComboBoxConfiguring() {
        dayListComboBox.removeAllItems();
        // d.m.y for the start of the career
        for (int num = 1; num <= 30; num++) {
            dayListComboBox.addItem("" + num);
        }

        monthListComboBox.removeAllItems();
        for (int num = 1; num <= 12; num++) {
            monthListComboBox.addItem("" + num);
        }

        yearListComboBox.removeAllItems();
        for (int num = 1989; num <= 2023; num++) {
            yearListComboBox.addItem("" + num);
        }

        // Hours and minutes for the start of the route
        hoursOfRouteStartListComboBox.removeAllItems();
        for (int num = 0; num <= 23; num++) {
            hoursOfRouteStartListComboBox.addItem("" + num);
        }

        minutesOfRouteStartListComboBox.removeAllItems();
        for (int num = 0; num < 60; num++) {
            minutesOfRouteStartListComboBox.addItem("" + num);
        }

        // Hours and minutes for the end of the route
        hoursOfRouteEndListComboBox.removeAllItems();
        for (int num = 0; num <= 23; num++) {
            hoursOfRouteEndListComboBox.addItem("" + num);
        }

        minutesOfRouteEndListComboBox.removeAllItems();
        for (int num = 0; num < 60; num++) {
            minutesOfRouteEndListComboBox.addItem("" + num);
        }
    }

    /**
     * Sets all labels to the one Font and HorizontalAlignment
     */
    private void labelsConfiguring() {
        for (JLabel l :
                labels) {
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        }
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
     * Updates components
     * - Configures labels
     * - Configures default items in ComboBoxes
     * - Panel adds components
     *
     * @return configured JPanel object
     */
    private JPanel updatePanelComponents() {
        labelsConfiguring();
        listComboBoxConfiguring();
        driverIDTextField.setEnabled(true);
        panelAddingComponents();
        return panel;
    }

    /**
     * Creates new object type of Driver depending on user's choice (Ok, Cancel).
     *
     * @param frame frame where JOptionPane should be shown
     * @return new created Driver object if all conditions have been met
     */
    public Driver createDriver(JFrame frame){
        int choice = JOptionPane.showConfirmDialog(null,
                updatePanelComponents(),
                "Driver creating",
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
                try {
                    Driver d = new Driver(
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            patronymicTextField.getText(),
                            phoneNumberTextField.getText(),
                            livingAddressTextField.getText(),
                            getDateOfStartOfCareerFromComboBox(),
                            getTimeOfStartOfRouteFromComboBox(),
                            getTimeOfEndOfRouteFromComboBox(),
                            nameOfRouteTextField.getText(),
                            driverIDTextField.getText()
                    );
                    clearAll();
                    return d;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame,
                            "The length of service is less than 1 year",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        return null;
    }

    /**
     * Initializes components using driver data
     *
     * @param driver object to get data
     */
    private void initializingComponentsUsingDriverData(Driver driver) {
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
    }

    /**
     * Updates components
     * - Configures labels
     * - Initializes components with driver data
     * - Adds components to the panel
     *
     * @param driver object to get data
     * @return configured JPanel object
     */
    private JPanel updatePanelComponentsUsingPreviousData(Driver driver) {
        labelsConfiguring();
        listComboBoxConfiguring();
        initializingComponentsUsingDriverData(driver);
        panelAddingComponents();
        return panel;
    }

    /**
     * Gets edited or not edited fields/comboBox with data and change fields of given driver
     *
     * @param frame  frame where JOptionPane should be shown
     * @param driver object whose fields should be edited
     */
    public void editDriver(JFrame frame, Driver driver) {
        int choice = JOptionPane.showConfirmDialog(null,
                updatePanelComponentsUsingPreviousData(driver),
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
                driver.setName(nameTextField.getText());
                driver.setSurname(surnameTextField.getText());
                driver.setPatronymic(patronymicTextField.getText());
                try {
                    driver.setPhoneNumber(phoneNumberTextField.getText());
                    driver.setId(driverIDTextField.getText());
                } catch (Exception ignored) {
                }
                driver.setLivingAddress(livingAddressTextField.getText());
                try {
                    driver.setStartOfCareer(getDateOfStartOfCareerFromComboBox());
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(frame,
                            "The length of service is less than 1 year",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
                driver.setStartOfRoute(getTimeOfStartOfRouteFromComboBox());
                driver.setEndOfRoute(getTimeOfEndOfRouteFromComboBox());
                driver.setRouteName(nameOfRouteTextField.getText());
            }
        }
        clearAll();
    }
}
