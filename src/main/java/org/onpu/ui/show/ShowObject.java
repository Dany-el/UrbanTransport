package org.onpu.ui.show;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.ui.graphics.pane_factory.GridLayoutFactory;

import javax.swing.*;

public final class ShowObject {
    /**
     * Displays the details of a Driver object through a graphical user interface.
     * The method creates a read-only text field for each driver detail and sets the text to the corresponding value in the Driver object.
     * The user can close the dialog by clicking the "Ok" or "Cancel" button.
     *
     * @param d the Driver object to display
     */
    public static void show(Driver d) {

        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Name");
        GridLayoutFactory.addTextField(d.getName(), false);

        GridLayoutFactory.addLabel("Surname");
        GridLayoutFactory.addTextField(d.getSurname(), false);

        GridLayoutFactory.addLabel("Patronymic");
        GridLayoutFactory.addTextField(d.getPatronymic(), false);

        GridLayoutFactory.addLabel("Living address");
        GridLayoutFactory.addTextField(d.getLivingAddress(), false);

        GridLayoutFactory.addLabel("Phone number");
        GridLayoutFactory.addTextField(d.getPhoneNumber(), false);

        GridLayoutFactory.addLabel("Start of career");
        GridLayoutFactory.addTextField(d.getStartOfCareer().toString(), false);

        GridLayoutFactory.addLabel("ID");
        GridLayoutFactory.addTextField(d.getId(), false);

        GridLayoutFactory.addLabel("Route");
        GridLayoutFactory.addTextField(d.getRouteName(), false);

        GridLayoutFactory.addLabel("Start time");
        GridLayoutFactory.addTextField("from " + d.getStartOfRoute().toString() + " to " + d.getEndOfRoute().toString(), false);

        JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Showing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays the details of a Transport object through a graphical user interface.
     * The method creates a read-only text field for each driver detail and sets the text to the corresponding value in the Transport object.
     * The user can close the dialog by clicking the "Ok" or "Cancel" button.
     *
     * @param t the Transport object to display
     */
    public static void show(Transport t) {

        GridLayoutFactory.removeComponents();

        GridLayoutFactory.addLabel("Route");
        GridLayoutFactory.addTextField(t.getRouteName(), false);

        GridLayoutFactory.addLabel("Type");
        GridLayoutFactory.addTextField(t.getType().toString(), false);

        GridLayoutFactory.addLabel("Licence (HH 0000 HH)");
        GridLayoutFactory.addTextField(t.getLicence(), false);

        GridLayoutFactory.addLabel("ID");
        GridLayoutFactory.addTextField(t.getId(), false);

        GridLayoutFactory.addLabel("Driver");
        Driver d = t.getDriver();
        String driverId = d == null ? "-not-selected-" : d.getId();
        GridLayoutFactory.addTextField(driverId, false);

        JOptionPane.showConfirmDialog(
                null,
                GridLayoutFactory.getPane(),
                "Showing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
