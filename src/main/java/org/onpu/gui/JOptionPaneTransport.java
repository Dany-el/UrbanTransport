package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

import javax.swing.*;
import java.awt.*;

public final class JOptionPaneTransport {
    private static final GridBagConstraints gbc = new GridBagConstraints();
    private static final JPanel panel = new JPanel(new GridBagLayout());
    private static final JLabel typeLabel = new JLabel("Type");
    private static final JTextField typeTextField = new JTextField();
    private static final JLabel idLabel = new JLabel("Transport ID (8-digits)");
    private static final JTextField idTextField = new JTextField();
    private static final JLabel numberLabel = new JLabel("Number (e.g. AA 3787 BB)");
    private static final JTextField numberTextField = new JTextField();
    private static final JLabel driverIDLabel = new JLabel("Driver ID");
    private static final JComboBox<String> driverIDComboBox = new JComboBox<>();
    //

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

        panel.add(idLabel, stackComponents(4));
        panel.add(idTextField, stackComponents(5));

        panel.add(numberLabel, stackComponents(6));
        panel.add(numberTextField, stackComponents(7));

        panel.add(driverIDLabel, stackComponents(8));
        panel.add(driverIDComboBox, stackComponents(9));
    }

    /**
     * Sets all texts fields to zero-value
     */
    private void clearAll() {
        typeTextField.setText("");
        idTextField.setText("");
        numberTextField.setText("");
        driverIDComboBox.setSelectedIndex(0);
    }

    /**
     * Updates ComboBox with Driver objects
     *
     * @param urbanCompany object to get Set of class Driver
     */
    private void updateDriverIDComboBox(UrbanCompany urbanCompany) {
        driverIDComboBox.removeAllItems();
        driverIDComboBox.addItem("000000");
        for (Driver d :
                urbanCompany.getGroupOfDrivers()) {
            driverIDComboBox.addItem(d.getId());
        }
    }

    /**
     * Updates components
     * - Configures labels
     * - Initializes components with transport data
     * - Panel adds components
     *
     * @param urbanCompany object to get Set of class Driver
     * @return configured JPanel object
     */
    private JPanel updatePanelComponents(UrbanCompany urbanCompany) {
        labelsConfiguring();
        updateDriverIDComboBox(urbanCompany);
        panelAddingComponents();
        return panel;
    }

    /**
     * Creates new object type of Transport depending on user's choice(Ok, Cancel).
     *
     * @param frame        frame that contains object of this class
     * @param urbanCompany UrbanCompany object
     * @return new created Transport object if all conditions have been met
     * @throws Exception if field numberTextField or idTextField does not match the conditions
     */
    public Transport createTransport(JFrame frame, UrbanCompany urbanCompany) throws Exception {
        int choice = JOptionPane.showConfirmDialog(null,
                updatePanelComponents(urbanCompany),
                "Transport creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            String driverID = driverIDComboBox.getItemAt(driverIDComboBox.getSelectedIndex());
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

    /**
     * Finds index of id in Set of class Driver
     *
     * @param id           id to find
     * @param urbanCompany where to find
     * @return index of driver in Set
     */
    private int findIndexByID(String id, UrbanCompany urbanCompany) {
        int index = 1;
        for (Driver d :
                urbanCompany.getGroupOfDrivers()) {
            if (!id.equals(d.getId()))
                index++;
            else {
                break;
            }
        }
        return index;
    }

    /**
     * Initializes components using transport data
     *
     * @param transport    object to get data
     * @param urbanCompany object to get Set of class Driver
     */
    private void initializingComponentsUsingTransportData(Transport transport, UrbanCompany urbanCompany) {
        typeTextField.setText(transport.getType());
        numberTextField.setText(transport.getNumber());
        idTextField.setText(transport.getId());
        idTextField.setEnabled(false);
        String driverID = transport.getDriver().getId();
        driverIDComboBox.removeAllItems();
        updateDriverIDComboBox(urbanCompany);
        if ("Undefined".equals(driverID)) {
            driverIDComboBox.setSelectedIndex(0);
        }
        else {
            driverIDComboBox.setSelectedIndex(findIndexByID(driverID, urbanCompany));
        }
    }

    /**
     * Update components
     * - Configures labels
     * - Initializes components with transport data
     * - Panel adds components
     *
     * @param transport    object to get data from
     * @param urbanCompany object to get Set of class Driver
     * @return configured JPanel object
     */
    private JPanel updatePanelComponentsUsingPreviousData(Transport transport, UrbanCompany urbanCompany) {
        labelsConfiguring();
        initializingComponentsUsingTransportData(transport, urbanCompany);
        panelAddingComponents();
        return panel;
    }

    /**
     * Gets edited or not edited fields/comboBox with data and change fields of given transport
     *
     * @param frame        frame where should JOptionPane be shown
     * @param transport    object whose fields are should be edited
     * @param urbanCompany object to get Set of class Driver
     * @return edited Transport object
     */
    public Transport editTransport(JFrame frame, Transport transport, UrbanCompany urbanCompany) {
        int choice = JOptionPane.showConfirmDialog(null,
                updatePanelComponentsUsingPreviousData(transport, urbanCompany),
                "Transport creating",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            String driverID = driverIDComboBox.getItemAt(driverIDComboBox.getSelectedIndex());
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
                transport.setDriver(driver);
            }
        }
        clearAll();
        return transport;
    }
}