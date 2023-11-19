package org.onpu.ui.create;

import org.onpu.controllers.DriverController;
import org.onpu.controllers.TransportController;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.entities.TransportType;
import org.onpu.ui.graphics.pane_factory.GridLayoutFactory;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class CreateObject {
    /**
     * Creates a new driver by prompting the user to input driver details through a graphical user interface.
     * The user is asked to input the driver's name, surname, patronymic, living address, phone number, start of career, ID, route, start time, and end time.
     * If the user confirms the input, a new Driver object is created with the provided details.
     * If an exception occurs during the creation of the Driver object, a warning message is displayed.
     *
     * @return a new Driver object if the user confirms the input, otherwise null
     */
    public static Driver createDriver() {
        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Name");
        JTextField nameField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Surname");
        JTextField surnameField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Patronymic");
        JTextField patronymicField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Living address");
        JTextField livingAddressField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Phone number");
        JTextField phoneNumberField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Start of career");

        JComboBox<Integer> yearComboBox = new JComboBox<>(
                IntStream.range(1989, 2025).
                        boxed().
                        toArray(Integer[]::new)
        );

        JComboBox<Integer> monthComboBox = new JComboBox<>(
                IntStream.range(1, 13).
                        boxed().
                        toArray(Integer[]::new)
        );

        JComboBox<Integer> dayComboBox = new JComboBox<>(
                IntStream.range(1, 32).
                        boxed().
                        toArray(Integer[]::new)
        );

        JPanel careerPanel = new JPanel(new GridLayout(1, 0));
        careerPanel.add(yearComboBox);
        careerPanel.add(monthComboBox);
        careerPanel.add(dayComboBox);

        GridLayoutFactory.addComponent(careerPanel);

        GridLayoutFactory.addLabel("ID");
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(6);
        nf.setGroupingUsed(false);
        JTextField idField = GridLayoutFactory.addFormattedTextField(nf);

        GridLayoutFactory.addLabel("Route");
        JTextField routeNameField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("Start time");
        JComboBox<Integer> startHoursComboBox = new JComboBox<>(
                IntStream.range(0, 24).
                        boxed().
                        toArray(Integer[]::new)
        );

        JComboBox<Integer> startMinutesComboBox = new JComboBox<>(
                IntStream.range(0, 60).
                        boxed().
                        toArray(Integer[]::new)
        );

        JPanel startTimePanel = new JPanel(new GridLayout(1, 0));
        startTimePanel.add(startHoursComboBox);
        startTimePanel.add(startMinutesComboBox);

        GridLayoutFactory.addComponent(startTimePanel);

        GridLayoutFactory.addLabel("End time");
        JComboBox<Integer> endHoursComboBox = new JComboBox<>(
                IntStream.range(0, 23).
                        boxed().
                        toArray(Integer[]::new)
        );

        JComboBox<Integer> endMinutesComboBox = new JComboBox<>(
                IntStream.range(0, 59).
                        boxed().
                        toArray(Integer[]::new)
        );

        JPanel endTimePanel = new JPanel(new GridLayout(1, 0));
        endTimePanel.add(endHoursComboBox);
        endTimePanel.add(endMinutesComboBox);

        GridLayoutFactory.addComponent(endTimePanel);

        int choice = JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            try {
                return new Driver(nameField.getText(), surnameField.getText(), patronymicField.getText(),
                        phoneNumberField.getText(), livingAddressField.getText(),
                        getLocalDateFromComboBox(yearComboBox, monthComboBox, dayComboBox),
                        getLocalTimeFromComboBox(startHoursComboBox, startMinutesComboBox),
                        getLocalTimeFromComboBox(endHoursComboBox, endMinutesComboBox),
                        routeNameField.getText(), idField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Creates new transport by prompting the user to input transport details through a graphical user interface.
     * The user is asked to input the transport's type, license, ID, and driver.
     * If the user confirms the input, a new Transport object is created with the provided details.
     * If an exception occurs during the creation of the Transport object, a warning message is displayed.
     *
     * @param dc the DriverController object used to retrieve a list of unattached drivers
     * @param tc the TransportController object (not used in this method)
     * @return a new Transport object if the user confirms the input, otherwise null
     */
    public static Transport createTransport(DriverController dc, TransportController tc) {
        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Type");
        JComboBox<TransportType> typeComboBox = GridLayoutFactory.addComboBox(List.of(
                TransportType.UNDEFINED,
                TransportType.BUS,
                TransportType.TROLLEYBUS,
                TransportType.TRAM,
                TransportType.FUNICULAR,
                TransportType.SUBWAY,
                TransportType.SHUTTLE_BUS));

        GridLayoutFactory.addLabel("Licence (HH 0000 HH)");
        JTextField licenceField = GridLayoutFactory.addTextField();

        GridLayoutFactory.addLabel("ID");
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(8);
        nf.setGroupingUsed(false);
        JTextField idField = GridLayoutFactory.addFormattedTextField(nf);

        GridLayoutFactory.addLabel("Driver");
        List<Driver> unattachedDrivers = getUnattachedDrivers(dc, tc);
        List<String> driverIDs;
        if (!unattachedDrivers.isEmpty()) {
            driverIDs = new ArrayList<>(unattachedDrivers.stream()
                    .map(Driver::getId)
                    .toList());
            driverIDs.add(0, "-not-selected-");
        } else {
            driverIDs = List.of("-not-selected-");
        }
        JComboBox<String> driverIdComboBox = GridLayoutFactory.addComboBox(driverIDs);

        int choice = JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            try {
                Transport t = new Transport(
                        (TransportType) typeComboBox.getSelectedItem(),
                        licenceField.getText(),
                        idField.getText());
                Driver d = dc.getById((String) driverIdComboBox.getSelectedItem());
                if (d != null)
                    t.setDriver(d);
                return t;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Returns a list of unattached drivers from a DriverController object and a TransportController object.
     * A driver is considered unattached if it is not linked to any transport.
     *
     * @param dc the DriverController object used to retrieve a list of drivers
     * @param tc the TransportController object used to retrieve a list of transports
     * @return a list of unattached drivers
     */
    private static List<Driver> getUnattachedDrivers(DriverController dc, TransportController tc) {
        List<Driver> unattachedDrivers = new ArrayList<>();

        for (Driver d : dc.getDriversList()) {
            boolean isLinked = false;
            for (Transport t : tc.getTransportsList()) {
                try {
                    if (t.getDriver().equals(d)) {
                        isLinked = true;
                        break;
                    }
                } catch (NullPointerException ignored) {
                }
            }
            if (!isLinked) {
                unattachedDrivers.add(d);
            }
        }

        return unattachedDrivers;
    }

    /**
     * Returns a LocalDate object from three JComboBox objects representing the year, month, and day.
     *
     * @param yearComboBox  the JComboBox object representing the year
     * @param monthComboBox the JComboBox object representing the month
     * @param dayComboBox   the JComboBox object representing the day
     * @return a LocalDate object representing the selected date
     */
    private static LocalDate getLocalDateFromComboBox(JComboBox<Integer> yearComboBox,
                                                      JComboBox<Integer> monthComboBox,
                                                      JComboBox<Integer> dayComboBox) {
        int year = yearComboBox.getItemAt(yearComboBox.getSelectedIndex());
        int month = monthComboBox.getItemAt(monthComboBox.getSelectedIndex());
        int day = dayComboBox.getItemAt(dayComboBox.getSelectedIndex());
        return LocalDate.of(year, month, day);
    }

    /**
     * Returns a LocalTime object from two JComboBox objects representing hours and minutes.
     *
     * @param hourComboBox    the JComboBox object representing the hours
     * @param minutesComboBox the JComboBox object representing the minutes
     * @return a LocalTime object representing the selected time
     */
    private static LocalTime getLocalTimeFromComboBox(JComboBox<Integer> hourComboBox,
                                                      JComboBox<Integer> minutesComboBox) {
        int hours = hourComboBox.getItemAt(hourComboBox.getSelectedIndex());
        int minutes = minutesComboBox.getItemAt(minutesComboBox.getSelectedIndex());
        return LocalTime.of(hours, minutes);
    }
}
