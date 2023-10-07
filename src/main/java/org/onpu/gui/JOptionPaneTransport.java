package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

import javax.swing.*;
import java.awt.*;

public class JOptionPaneTransport {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JLabel typeLabel = new JLabel("Type");
    private final JTextField typeTextField = new JTextField();
    private final JLabel routeNameLabel = new JLabel("Route name");
    private final JTextField routeNameTextField = new JTextField();
    private final JLabel idLabel = new JLabel("Transport ID (8-digits)");
    private final JTextField idTextField = new JTextField();
    private final JLabel numberLabel = new JLabel("Number (e.g. AA 3787 BB)");
    private final JTextField numberTextField = new JTextField();
    private final JLabel driverIDLabel = new JLabel("Driver ID");
    private final JTextField driverIDTextField = new JTextField(6);

    /**
     * @param gridy - place at y-grid
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
     * Configuring all labels
     */
    private void labelsConfiguring() {
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        routeNameLabel.setHorizontalAlignment(JLabel.CENTER);
        routeNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        numberLabel.setHorizontalAlignment(JLabel.CENTER);
        numberLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        driverIDLabel.setHorizontalAlignment(JLabel.CENTER);
        driverIDLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    /**
     * Adding all components to the panel
     */
    private void panelAddingComponents() {
        panel.add(typeLabel, stackComponents(0));
        panel.add(typeTextField, stackComponents(1));

        panel.add(routeNameLabel, stackComponents(2));
        panel.add(routeNameTextField, stackComponents(3));

        panel.add(idLabel, stackComponents(4));
        panel.add(idTextField, stackComponents(5));

        panel.add(numberLabel, stackComponents(6));
        panel.add(numberTextField, stackComponents(7));

        panel.add(driverIDLabel, stackComponents(8));
        panel.add(driverIDTextField, stackComponents(9));
    }

    /**
     * Sets all text fields to zero-value
     */
    private void clearAll() {
        typeTextField.setText("");
        routeNameTextField.setText("");
        idTextField.setText("");
        numberTextField.setText("");
        driverIDTextField.setText("");
    }

    /**
     * Groups up all configuration
     *
     * @return Configured panel with configured components
     */
    private JPanel panelWithConfiguredComponents() {
        labelsConfiguring();
        panelAddingComponents();
        return panel;
    }

    /**
     * Creates new Transport depending on user's choice(Ok, Cancel).
     *
     * @param frame        frame that contains object of this class
     * @param urbanCompany UrbanCompany object
     * @return new created Transport object if all conditions have been met
     * @throws Exception if field numberTextField or idTextField does not match the conditions
     */
    public Transport createTransport(JFrame frame, UrbanCompany urbanCompany) throws Exception {
        int choice = JOptionPane.showConfirmDialog(null,
                panelWithConfiguredComponents(),
                "Transport creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            String driverID = driverIDTextField.getText();
            Driver driver;
            if ("000000".equals(driverID)) {
                driver = new Driver();
            } else {
                driver = urbanCompany.getDriverBy(driverID);
            }
            if (driver == null) {
                JOptionPane.showMessageDialog(frame, "Driver not found. Try 000000 if driver's not already exist",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Transport t = new Transport(
                        typeTextField.getText(),
                        numberTextField.getText(),
                        idTextField.getText(),
                        driver
                );
                clearAll();
                return t;
            }
        }
        return null;
    }

    private JPanel setTransportDataToTextFields(Transport transport) {
        labelsConfiguring();

        typeTextField.setText(transport.getType());
        numberTextField.setText(transport.getNumber());
        routeNameTextField.setText(transport.getRouteName());
        idTextField.setText(transport.getId());
        idTextField.setEnabled(false);
        String transportID = transport.getDriver().getId();
        if ("Undefined".equals(transportID))
            driverIDTextField.setText("000000");
        else
            driverIDTextField.setText(transportID);

        panelAddingComponents();
        return panel;
    }

    public Transport editTransport(JFrame frame, Transport transport, UrbanCompany urbanCompany) {
        int choice = JOptionPane.showConfirmDialog(null,
                setTransportDataToTextFields(transport),
                "Transport creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            String driverID = driverIDTextField.getText();
            Driver driver;
            if ("000000".equals(driverID)) {
                driver = new Driver();
            } else {
                driver = urbanCompany.getDriverBy(driverID);
            }
            if (driver == null) {
                JOptionPane.showMessageDialog(frame,
                        "Driver not found. Try 000000 if driver's not already exist",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    transport.setType(typeTextField.getText());
                    transport.setId(idTextField.getText());
                    transport.setNumber(numberTextField.getText());
                } catch (Exception ignored) {
                }
                transport.setRouteName(routeNameTextField.getText());
                transport.setDriver(driver);
            }
        }
        return transport;
    }
}


