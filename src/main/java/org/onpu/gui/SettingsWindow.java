package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsWindow extends JFrame {
    /* Main panel components */
    private final JPanel mainPanel = new JPanel();
    private final JComboBox<String> listComboBox = new JComboBox<>();
    private final JButton addButton = new JButton("+");
    private final JButton removeButton = new JButton("－");
    private final JButton editButton = new JButton("edit ✎");
    private final JLabel dataLabel = new JLabel("Data", SwingConstants.CENTER);
    private final JButton showButton = new JButton("Show");
    private final JOptionPaneDriver optionPaneDriver = new JOptionPaneDriver();
    private final JOptionPaneTransport optionPaneTransport = new JOptionPaneTransport();

    /* Scrollable list panel components */
    private final JPanel scrollableListPanel = new JPanel();
    private final DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private final JList<String> idList = new JList<>(defaultListModel);
    private final JScrollPane scrollPane = new JScrollPane(idList);
    /* Needed for opening only 1 window*/
    private static boolean isOpened = false;
    /* */
    private String selectedItemInComboBox;
    private String selectedIdInList;
    private UrbanCompany urbanCompany;

    // TODO methods to add/delete/edit elements

    public SettingsWindow() {
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

        /* Adding components to the panels */
        panelsAddingComponents();
    }

    /**
     * Place where all components are added to needed panels
     */
    private void panelsAddingComponents() {
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.decode("#d6f3fa"));

        scrollableListPanel.setLayout(new BorderLayout());
        scrollableListPanel.setBounds(50, 100, 200, 250);

        scrollableListPanel.add(scrollPane);

        mainPanel.add(showButton);
        mainPanel.add(scrollableListPanel);
        mainPanel.add(listComboBox);
        mainPanel.add(addButton);
        mainPanel.add(removeButton);
        mainPanel.add(editButton);
        mainPanel.add(dataLabel);
    }

    private void addButtonConfiguration() {
        addButton.setBounds(150, 400, 100, 50);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {
                System.out.println("Driver selected");
                try {
                    Driver d = optionPaneDriver.createDriver(this);
                    if (d != null)
                        urbanCompany.employDriver(d);
                } catch (Exception ignored) {
                }
                driverListUpdate();
            } else if ("Transport".equals(listComboBox.getSelectedItem())) {
                System.out.println("Transport selected");
                try {
                    Transport t = optionPaneTransport.createTransport(this, urbanCompany);
                    if (t != null)
                        urbanCompany.addTransportToDepot(t);
                } catch (Exception ignored) {
                }
                transportListUpdate();
            } else if ("Dispatcher".equals(listComboBox.getSelectedItem())) {
                System.out.println("Dispatcher selected");
                // TODO dispatcher add
            }
        });
    }

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

    private void editButtonConfiguration() {
        editButton.setBounds(450, 400, 100, 50);
        editButton.setFont(new Font("Arial", Font.BOLD, 20));
        editButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {
                System.out.println("Driver selected");

                Driver d = urbanCompany.getDriverBy(selectedIdInList);
                d = optionPaneDriver.editDriver(this, d);
                urbanCompany.getDriverBy(selectedIdInList).copyFrom(d);
                urbanCompany.saveGroupOfDrivers();

            } else if ("Transport".equals(listComboBox.getSelectedItem())) {
                System.out.println("Transport selected");

                Transport t = urbanCompany.getTransportBy(selectedIdInList);
                t = optionPaneTransport.editTransport(this, t, urbanCompany);
                urbanCompany.getTransportBy(selectedIdInList).copyFrom(t);
                urbanCompany.saveDepot();

            } else if ("Dispatcher".equals(listComboBox.getSelectedItem())) {
                System.out.println("Dispatcher selected");
                // TODO dispatcher edit
            }
        });
    }

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

    private void dataLabelConfiguration() {
        dataLabel.setBounds(350, 65, 300, 285);
        dataLabel.setBorder(new LineBorder(Color.BLACK));
        dataLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    private void idListConfiguration() {
        idList.setBounds(50, 100, 200, 250);
        idList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        idList.addListSelectionListener(e -> {
            if (!idList.isSelectionEmpty())
                selectedIdInList = idList.getSelectedValue();
        });
    }

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
    @org.jetbrains.annotations.NotNull
    private String plainTextToHTML(String plainText) {
        String temp = plainText;
        temp = temp.replaceAll("\n", "<br/>");
        temp = "<html>" + temp + "<html/>";
        return temp;
    }

    /**
     *
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
     *
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
     *
     */
    private void dispatcherListUpdate() {
        defaultListModel.clear();
        dataLabel.setText("");
        // TODO dispatcher update
    }

    public void setUrbanCompany(UrbanCompany urbanCompany) {
        this.urbanCompany = urbanCompany;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void start() {
        isOpened = true;
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.add(mainPanel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isOpened = false;
            }
        });
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
