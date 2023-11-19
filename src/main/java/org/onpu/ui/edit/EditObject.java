package org.onpu.ui.edit;

import org.onpu.controllers.DriverController;
import org.onpu.controllers.TransportController;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.ui.graphics.pane_factory.GridLayoutFactory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class EditObject {
    /**
     * Displays the details of a Driver object through a graphical user interface for editing.
     * The method creates input fields for each driver detail and sets the initial text to the corresponding value in the Driver object.
     * It also provides options for selecting the start and end times for the driver's route.
     * The user can confirm the changes by clicking the "OK" button.
     * If confirmed, the method updates the Driver object with the modified details.
     *
     * @param d  the Driver object to edit
     * @param tc the TransportController object (not used in this method)
     */
    public static void edit(Driver d, TransportController tc) {
        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Name");
        JTextField nameField = GridLayoutFactory.addTextField(d.getName());

        GridLayoutFactory.addLabel("Surname");
        JTextField surnameField = GridLayoutFactory.addTextField(d.getSurname());

        GridLayoutFactory.addLabel("Patronymic");
        JTextField patronymicField = GridLayoutFactory.addTextField(d.getPatronymic());

        GridLayoutFactory.addLabel("Living address");
        JTextField livingAddressField = GridLayoutFactory.addTextField(d.getLivingAddress());

        GridLayoutFactory.addLabel("Phone number");
        JTextField phoneNumberField = GridLayoutFactory.addTextField(d.getPhoneNumber());

        GridLayoutFactory.addLabel("Start of career");
        GridLayoutFactory.addTextField(d.getStartOfCareer().toString(), false);

        GridLayoutFactory.addLabel("ID");
        GridLayoutFactory.addTextField(d.getId(), false);

        GridLayoutFactory.addLabel("Route");
        JTextField routeNameField = GridLayoutFactory.addTextField(d.getRouteName());

        GridLayoutFactory.addLabel("Start time");
        JComboBox<Integer> startHoursComboBox = new JComboBox<>(
                IntStream.range(0, 24).
                        boxed().
                        toArray(Integer[]::new)
        );
        startHoursComboBox.setSelectedIndex(d.getStartOfRoute().getHour());

        JComboBox<Integer> startMinutesComboBox = new JComboBox<>(
                IntStream.range(0, 60).
                        boxed().
                        toArray(Integer[]::new)
        );
        startMinutesComboBox.setSelectedIndex(d.getStartOfRoute().getMinute());

        JPanel startTimePanel = new JPanel(new GridLayout(1, 0));
        startTimePanel.add(startHoursComboBox);
        startTimePanel.add(startMinutesComboBox);

        GridLayoutFactory.addComponent(startTimePanel);

        GridLayoutFactory.addLabel("End time");
        JComboBox<Integer> endHoursComboBox = new JComboBox<>(
                IntStream.range(0, 24).
                        boxed().
                        toArray(Integer[]::new)
        );

        endHoursComboBox.setSelectedIndex(d.getEndOfRoute().getHour());

        JComboBox<Integer> endMinutesComboBox = new JComboBox<>(
                IntStream.range(0, 60).
                        boxed().
                        toArray(Integer[]::new)
        );

        endMinutesComboBox.setSelectedIndex(d.getEndOfRoute().getMinute());

        JPanel endTimePanel = new JPanel(new GridLayout(1, 0));
        endTimePanel.add(endHoursComboBox);
        endTimePanel.add(endMinutesComboBox);

        GridLayoutFactory.addComponent(endTimePanel);

        int choice = JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Editing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            try {
                d.setName(nameField.getText());
                d.setSurname(surnameField.getText());
                d.setPatronymic(patronymicField.getText());
                d.setLivingAddress(livingAddressField.getText());
                d.setPhoneNumber(phoneNumberField.getText());
                d.setRouteName(routeNameField.getText());
                Transport transport = getTransport(d, tc);
                if (transport != null)
                    transport.setRouteName(routeNameField.getText());
                d.setStartOfRoute(getLocalTimeFromComboBox(startHoursComboBox, startMinutesComboBox));
                d.setEndOfRoute(getLocalTimeFromComboBox(endHoursComboBox, endMinutesComboBox));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Displays the details of a Transport object through a graphical user interface for editing.
     * The method creates read-only text fields for each transport detail and sets the text to the corresponding value in the Transport object.
     * It also provides a drop-down list of unattached drivers for selection.
     * The user can confirm the changes by clicking the "OK" button.
     * If confirmed, the method updates the driver associated with the transport based on the user's selection.
     *
     * @param t  the Transport object to edit
     * @param dc the DriverController object used to retrieve a list of unattached drivers
     * @param tc the TransportController object (not used in this method)
     */
    public static void edit(Transport t, DriverController dc, TransportController tc) {
        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Type");
        GridLayoutFactory.addTextField(t.getType().getValue(), false);

        GridLayoutFactory.addLabel("Licence (HH 0000 HH)");
        GridLayoutFactory.addTextField(t.getLicence(), false);

        GridLayoutFactory.addLabel("ID");
        GridLayoutFactory.addTextField(t.getId(), false);

        GridLayoutFactory.addLabel("Driver");
        List<Driver> unattachedDrivers = getUnattachedDrivers(dc, tc);
        List<String> driverIDs;
        if (!unattachedDrivers.isEmpty()) {
            driverIDs = new ArrayList<>(unattachedDrivers.stream()
                    .map(Driver::getId)
                    .toList());
            if (t.getDriver() == null) {
                driverIDs.add(0, "-not-selected-");
            } else {
                driverIDs.add(0, t.getDriver().getId());
                driverIDs.add(1, "-not-selected-");
            }
        } else {
            driverIDs = new ArrayList<>(List.of("-not-selected-"));
            if (t.getDriver() != null) {
                driverIDs.add(0, t.getDriver().getId());
            }
        }
        JComboBox<String> driverIdComboBox = GridLayoutFactory.addComboBox(driverIDs);

        int choice = JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Editing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (choice == JOptionPane.OK_OPTION) {
            t.setDriver(dc.getById((String) driverIdComboBox.getSelectedItem()));
        }
    }

    /**
     * Returns a Transport object associated with the given Driver from a TransportController object.
     *
     * @param d  the Driver object
     * @param tc the TransportController object used to retrieve the list of transports
     * @return a Transport object associated with the given Driver, or null if not found
     */
    private static Transport getTransport(Driver d, TransportController tc) {
        return tc.getTransportsList().stream()
                .filter(t -> t.getDriver().equals(d))
                .findFirst()
                .orElse(null);
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
