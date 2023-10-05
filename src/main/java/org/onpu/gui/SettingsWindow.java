package org.onpu.gui;

import org.onpu.UrbanCompany;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class SettingsWindow extends JFrame {
    /* Main panel components */
    private JPanel mainPanel = new JPanel();
    private JComboBox<String> listComboBox = new JComboBox<>();
    private JButton addButton = new JButton("+");
    private JButton removeButton = new JButton("－");
    private JButton editButton = new JButton("edit ✎");
    private JLabel dataLabel = new JLabel();

    /* Scrollable list panel components */
    private JPanel scrollableListPanel = new JPanel();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JList<String> idList = new JList<>(defaultListModel);
    private JScrollPane scrollPane = new JScrollPane(idList);
    /* Needed for opening only 1 window*/
    private static boolean isOpened = false;
    /* */
    private UrbanCompany urbanCompany;

    // TODO methods to add/delete/edit elements

    public SettingsWindow() {
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.decode("#d6f3fa"));

        scrollableListPanel.setLayout(new BorderLayout());
        scrollableListPanel.setBounds(50, 100, 200, 250);

        listComboBox.setBounds(50, 50, 200, 50);
        listComboBox.addItem("Driver");
        listComboBox.addItem("Transport");
        listComboBox.addItem("Dispatcher");
        listComboBox.addItemListener(e -> {
            if ("Driver".equals(e.getItem())) {
                driverListUpdate();
            } else if ("Transport".equals(e.getItem())) {
                transportListUpdate();
            }
            // TODO add dispatcher
        });

        dataLabel.setBounds(350, 65, 300, 285);
        dataLabel.setBorder(new LineBorder(Color.BLACK));

        /* Buttons below the list*/
        addButton.setBounds(150, 400, 100, 50);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> {
            if ("Driver".equals(listComboBox.getSelectedItem())) {

                // TODO put all this code in method

                try {
                    urbanCompany.employDriver(new Driver(
                            "Name", "Surname", "P",
                            "0689782508", "Somewhere",
                            LocalDate.of(2004, 3, 26), LocalTime.of(10, 20), LocalTime.of(21, 30),
                            "North-West", "837462"
                    ));
                } catch (Exception ignored) {
                }

                driverListUpdate();
                /* just for example that it exists */
            } else if (("Transport".equals(listComboBox.getSelectedItem()))) {

            }


        });

        removeButton.setBounds(300, 400, 100, 50);
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> {

        });

        editButton.setBounds(450, 400, 100, 50);
        editButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> {

        });

        /* list in which vehicle/driver identifiers are contained */
        idList.setBounds(50, 100, 200, 250);
        idList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        idList.addListSelectionListener(e -> {
            // TODO display data. debug it
            dataLabel.setText(urbanCompany.getDriverBy(idList.getSelectedValue()).toString());
        });

        /* Adding components to the panels */
        scrollableListPanel.add(scrollPane);

        mainPanel.add(scrollableListPanel);
        mainPanel.add(listComboBox);
        mainPanel.add(addButton);
        mainPanel.add(removeButton);
        mainPanel.add(editButton);
        mainPanel.add(dataLabel);
    }

    private void transportListUpdate() {
        defaultListModel.clear();
        for (Transport d :
                urbanCompany.getDepot()) {
            defaultListModel.addElement("#" + d.getId());
        }
    }

    private void driverListUpdate() {
        defaultListModel.clear();
        for (Driver d :
                urbanCompany.getGroupOfDrivers()) {
            defaultListModel.addElement("#" + d.getId());
        }
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
