package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.onpu.gui.ScrollableListPanel.defaultListModel;
import static org.onpu.gui.ScrollableListPanel.idList;

public class SettingsWindowPanel extends JPanel {
    private static final JComboBox<String> listComboBox = new JComboBox<>();
    private static final JButton addButton = new JButton("+");
    private static final JButton removeButton = new JButton("－");
    private static final JButton editButton = new JButton("edit ✎");
    private static final JLabel dataLabel = new JLabel("Data", SwingConstants.CENTER);
    private static final JButton showButton = new JButton("Show");
    private static final JOptionPaneDriver optionPaneDriver = new JOptionPaneDriver();
    private static final JOptionPaneTransport optionPaneTransport = new JOptionPaneTransport();
    private static final ScrollableListPanel scrollableListPanel = new ScrollableListPanel();
    private static String selectedItemInComboBox;
    private static String selectedIdInList;
    private final UrbanCompany urbanCompany;
    private static JFrame workingFrame;

    public SettingsWindowPanel(JFrame frame, UrbanCompany urbanCompany){
        workingFrame = frame;
        this.urbanCompany = urbanCompany;

        /* Configuring list combo box */
        listComboBoxConfiguration();

        /* Configuring label */
        dataLabelConfiguration();

        /* Configuring buttons below the list */
        addButtonConfiguration();
        removeButtonConfiguration();
        editButtonConfiguration();
        showButtonConfiguration();

        /* Configuring list in which vehicle/driver identifiers are contained */
        idListConfiguration();

        panelAddingComponents();
    }

    /**
     * Place where all components are added to needed panels
     */
    private void panelAddingComponents() {
        this.setLayout(null);
        this.setBackground(Color.decode("#d6f3fa"));
        this.add(showButton);
        this.add(scrollableListPanel);
        this.add(listComboBox);
        this.add(addButton);
        this.add(removeButton);
        this.add(editButton);
        this.add(dataLabel);
    }

    /**
     * AddButton configuration
     * - Place
     * - Look
     * - ActionListener
     */
    private void addButtonConfiguration() {
        addButton.setBounds(150, 400, 100, 50);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {
                System.out.println("Driver selected");
                Driver d = optionPaneDriver.createDriver(workingFrame);
                if (d != null)
                    urbanCompany.employDriver(d);
                driverListUpdate();
            } else if ("Transport".equals(listComboBox.getSelectedItem())) {
                System.out.println("Transport selected");
                Transport t = optionPaneTransport.createTransport(workingFrame, urbanCompany);
                if (t != null)
                    urbanCompany.addTransportToDepot(t);
                transportListUpdate();
            } else if ("Dispatcher".equals(listComboBox.getSelectedItem())) {
                System.out.println("Dispatcher selected");
                // TODO dispatcher add
            }
        });
    }

    /**
     * RemoveButton configuration
     * - Place
     * - Look
     * - ActionListener
     */
    private void removeButtonConfiguration() {
        removeButton.setBounds(300, 400, 100, 50);
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {
                System.out.println("Driver selected");
                try {
                    urbanCompany.fireDriver(selectedIdInList);
                } catch (Exception ignored) {}
                driverListUpdate();
            } else if ("Transport".equals(listComboBox.getSelectedItem())) {
                System.out.println("Transport selected");
                try {
                    urbanCompany.removeTransportFromDepot(selectedIdInList);
                } catch (Exception ignored) {}
                transportListUpdate();
            } else if ("Dispatcher".equals(listComboBox.getSelectedItem())) {
                System.out.println("Dispatcher selected");
                // TODO dispatcher remove
            }
        });
    }

    /**
     * EditButton configuration
     * - Place
     * - Look
     * - ActionListener
     */
    private void editButtonConfiguration() {
        editButton.setBounds(450, 400, 100, 50);
        editButton.setFont(new Font("Arial", Font.BOLD, 20));
        editButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {
                System.out.println("Driver selected");

                Driver d = urbanCompany.getDriverBy(selectedIdInList);
                optionPaneDriver.editDriver(workingFrame, d);
                urbanCompany.getDriverBy(selectedIdInList).copyFrom(d);
                urbanCompany.saveGroupOfDrivers();

            } else if ("Transport".equals(listComboBox.getSelectedItem())) {
                System.out.println("Transport selected");

                Transport t = urbanCompany.getTransportBy(selectedIdInList);
                optionPaneTransport.editTransport(workingFrame, t, urbanCompany);
                urbanCompany.getTransportBy(selectedIdInList).copyFrom(t);
                urbanCompany.saveDepot();

            } else if ("Dispatcher".equals(listComboBox.getSelectedItem())) {
                System.out.println("Dispatcher selected");
                // TODO dispatcher edit
            }
        });
    }

    /**
     * ShowButton configuration
     * - Place
     * - Look
     * - ActionListener
     */
    private void showButtonConfiguration() {
        showButton.setBounds(250, 303, 100, 50);
        showButton.addActionListener(e -> {
            String plainText = "";
            if ("Driver".equals(selectedItemInComboBox)) {
                dataLabel.setText("");
                Driver foundDriver = urbanCompany.getDriverBy(selectedIdInList);
                plainText = foundDriver == null ? "" : foundDriver.toString();
            } else if ("Transport".equals(selectedItemInComboBox)) {
                dataLabel.setText("");
                Transport foundTransport = urbanCompany.getTransportBy(selectedIdInList);
                plainText = foundTransport == null ? "" : foundTransport.toString();
            } else if ("Dispatcher".equals(selectedItemInComboBox)) {
                dataLabel.setText("");
                // TODO dispatcher show
            }
            dataLabel.setText(plainTextToHTML(plainText));
        });
    }

    /**
     * Configuration of dataLabel
     */
    private void dataLabelConfiguration() {
        dataLabel.setBounds(350, 65, 300, 285);
        dataLabel.setBorder(new LineBorder(Color.BLACK));
        dataLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    /**
     * Configuration of idList
     */
    private void idListConfiguration() {
        idList.setBounds(50, 100, 200, 250);
        idList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        idList.addListSelectionListener(e -> {
            if (!idList.isSelectionEmpty())
                selectedIdInList = idList.getSelectedValue();
        });
    }

    /**
     * Configuration of ComboBox
     */
    private void listComboBoxConfiguration() {
        listComboBox.setBounds(50, 50, 200, 50);
        listComboBox.addItem("Nothing selected");
        listComboBox.addItem("Driver");
        listComboBox.addItem("Transport");
        listComboBox.addItem("Dispatcher");
        listComboBox.addActionListener(e -> {
            selectedItemInComboBox = listComboBox.getItemAt(listComboBox.getSelectedIndex());
            if ("Driver".equals(selectedItemInComboBox)) {
                System.out.println("Driver selected");
                driverListUpdate();
            } else if ("Transport".equals(selectedItemInComboBox)) {
                System.out.println("Transport selected");
                transportListUpdate();
            } else if ("Dispatcher".equals(selectedItemInComboBox)) {
                System.out.println("Dispatcher selected");
                dispatcherListUpdate();
            }
        });
    }

    /**
     * Reformat code to HTML format
     *
     * @param plainText text with '\n'
     * @return html formatted text
     */
    private String plainTextToHTML(String plainText) {
        String temp = plainText;
        temp = temp.replaceAll("\n", "<br/>");
        temp = "<html>" + temp + "<html/>";
        return temp;
    }

    /**
     * Updates transportList
     */
    private void transportListUpdate() {
        defaultListModel.clear();
        dataLabel.setText("");
        for (Transport t :
                urbanCompany.getDepot()) {
            defaultListModel.addElement(t.getId());
        }
    }

    /**
     * Update driverList
     */
    private void driverListUpdate() {
        defaultListModel.clear();
        dataLabel.setText("");
        for (Driver d :
                urbanCompany.getGroupOfDrivers()) {
            defaultListModel.addElement(d.getId());
        }
    }

    /**
     * Updates dispatcherList (?)
     */
    private void dispatcherListUpdate() {
        defaultListModel.clear();
        dataLabel.setText("");
        // TODO dispatcher update
    }
}
