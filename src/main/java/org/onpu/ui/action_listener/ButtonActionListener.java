package org.onpu.ui.action_listener;

import org.onpu.Connector;
import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.ui.create.CreateObject;
import org.onpu.ui.edit.EditObject;
import org.onpu.ui.graphics.settings.SettingsWindow;
import org.onpu.ui.show.ShowObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;

public final class ButtonActionListener {
    public static ActionListener getAddObjectActionListener(JComboBox<String> comboBox, DefaultListModel<String> listModel) {
        return e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            if (selectedItem != null) {
                switch (selectedItem) {
                    case "Driver":
                        Driver d = CreateObject.createDriver();
                        if (d != null) {
                            d.setNameOfOrganization(Connector.getUrbanCompany().getNameOfCompany());
                            Connector.getUrbanCompany().getDriverController().add(d);
                            updateList(selectedItem, listModel);
                        }
                        break;
                    case "Transport":
                        Transport t = CreateObject.createTransport(
                                Connector.getUrbanCompany().getDriverController(),
                                Connector.getUrbanCompany().getTransportController()
                        );
                        if (t != null) {
                            Connector.getUrbanCompany().getTransportController().add(t);
                            updateList(selectedItem, listModel);
                        }
                        break;
                    default:
                        listModel.clear();
                        break;
                }
            }
        };
    }

    public static ActionListener getRemoveObjectActionListener(JComboBox<String> comboBox, JList<String> idList, DefaultListModel<String> listModel) {
        return e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            String selectedId = idList.getSelectedValue();
            if (selectedItem != null && selectedId != null) {
                switch (selectedItem) {
                    case "Driver":
                        Connector.getUrbanCompany().getDriverController().remove(selectedId);
                        updateList(selectedItem, listModel);
                        break;
                    case "Transport":
                        Connector.getUrbanCompany().getTransportController().remove(selectedId);
                        updateList(selectedItem, listModel);
                        break;
                    default:
                        listModel.clear();
                        break;
                }
            }
        };
    }

    public static ActionListener getEditObjectActionListener(JComboBox<String> comboBox, JList<String> idList, DefaultListModel<String> listModel) {
        return e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            String selectedId = idList.getSelectedValue();
            if (selectedItem != null && selectedId != null) {
                switch (selectedItem) {
                    case "Driver":
                        Driver d = Connector.getUrbanCompany().getDriverController().getById(selectedId);
                        if (d != null) {
                            EditObject.edit(d, Connector.getUrbanCompany().getTransportController());
                            Connector.getUrbanCompany().getDriverController().updateSerializedFile();
                        }
                        break;
                    case "Transport":
                        Transport t = Connector.getUrbanCompany().getTransportController().getById(selectedId);
                        if (t != null) {
                            EditObject.edit(
                                    t,
                                    Connector.getUrbanCompany().getDriverController(),
                                    Connector.getUrbanCompany().getTransportController()
                            );
                            Connector.getUrbanCompany().getTransportController().updateSerializedFile();
                        }
                        break;
                    default:
                        listModel.clear();
                        break;
                }
            }
        };
    }

    public static ActionListener getShowObjectActionListener(JComboBox<String> comboBox, JList<String> idList, DefaultListModel<String> listModel) {
        return e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            String selectedId = idList.getSelectedValue();
            if (selectedItem != null && selectedId != null) {
                switch (selectedItem) {
                    case "Driver":
                        Driver d = Connector.getUrbanCompany().getDriverController().getById(selectedId);
                        if (d != null) {
                            ShowObject.show(d);
                        }
                        break;
                    case "Transport":
                        Transport t = Connector.getUrbanCompany().getTransportController().getById(selectedId);
                        if (t != null) {
                            ShowObject.show(t);
                        }
                        break;
                    default:
                        listModel.clear();
                        break;
                }
            }
        };
    }

    public static ActionListener getSettingsActionListener() {
        return e -> {
            if (!SettingsWindow.isOpened()){
                SettingsWindow.drawFrame();
            }
        };
    }

    public static void updateList(String selectedItem, DefaultListModel<String> listModel) {
        switch (selectedItem) {
            case "Driver":
                listModel.clear();
                for (Driver d :
                        Connector.getDriverList()) {
                    listModel.addElement(d.getId());
                }
                break;
            case "Transport":
                listModel.clear();
                for (Transport t :
                        Connector.getTransportList()) {
                    listModel.addElement(t.getId());
                }
                break;
            default:
                listModel.clear();
                break;
        }
    }

    public static class Requests {
        public static ActionListener getDriversActionListener(JPanel panel, JLabel mainLabel) {
            return e -> {
                List<Driver> listOfDrivers = Connector.getUrbanCompany().getDriverController().getDriversList();
                mainLabel.setText("Count: " + listOfDrivers.size());
                if (!listOfDrivers.isEmpty()) {
                    DefaultListModel<String> defaultListModel = new DefaultListModel<>();
                    JList<String> idList = new JList<>(defaultListModel);
                    JScrollPane scrollPane = new JScrollPane(idList);

                    for (Driver d :
                            listOfDrivers) {
                        defaultListModel.addElement(d.getId());
                    }
                    JOptionPane.showConfirmDialog(panel, scrollPane,
                            "IDs", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
            };
        }

        public static ActionListener getAverageDriverWorkingTimeActionListener(JPanel panel, JLabel mainLabel) {
            return e -> {
                double avgWorkingTime = Connector.getUrbanCompany().getAverageDriverWorkingTime();
                if (avgWorkingTime > 0) {
                    mainLabel.setText("Avg Time: " + String.format("%.2f", avgWorkingTime));
                } else {
                    JOptionPane.showMessageDialog(panel, "The list with drivers is empty",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            };
        }

        public static ActionListener getAverageLengthOfServiceActionListener(JPanel panel, JLabel mainLabel) {
            return e -> {
                double avgLengthOfService = Connector.getUrbanCompany().getAverageLengthOfService();
                if (avgLengthOfService > 0) {
                    mainLabel.setText("Avg Years: " + String.format("%.2f", avgLengthOfService));
                } else {
                    JOptionPane.showMessageDialog(panel, "The list with drivers is empty",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            };
        }

        public static ActionListener getDriverWithGreatLengthOfServiceActionListener(JPanel panel, JLabel mainLabel) {
            return e -> {
                Driver driver = null;
                try {
                    driver = Connector.getUrbanCompany().getDriverWithGreatLengthOfService();
                } catch (Exception exception) {
                    exception.printStackTrace(System.out);
                    JOptionPane.showMessageDialog(panel, "The list with drivers is empty",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                } finally {
                    if (driver != null) {
                        mainLabel.setText("ID " + driver.getId());
                    }
                }
            };
        }

        public static ActionListener getDriversOfSpecificRouteActionListener(JPanel panel, JLabel mainLabel) {
            return e -> {
                String routeName = JOptionPane.showInputDialog(panel, "Route name");
                if (routeName != null) {
                    List<Driver> listOfDrivers = Connector.getUrbanCompany().getDriversOfSpecificRoute(routeName);
                    if (!listOfDrivers.isEmpty()) {
                        mainLabel.setText("Count: " + listOfDrivers.size());
                        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
                        JList<String> idList = new JList<>(defaultListModel);
                        JScrollPane scrollPane = new JScrollPane(idList);

                        for (Driver d :
                                listOfDrivers) {
                            defaultListModel.addElement(d.getId());
                        }
                        JOptionPane.showConfirmDialog(panel, scrollPane,
                                "IDs", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Not found",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            };
        }

        public static ActionListener getCountOfTransportsAtTimeActionListener(JLabel mainLabel) {
            return e -> {
                JComboBox<Integer> hourComboBox = new JComboBox<>();
                for (int i = 0; i <= 23; i++) {
                    hourComboBox.addItem(i);
                }

                JComboBox<Integer> minutesComboBox = new JComboBox<>();
                for (int i = 0; i < 60; i++) {
                    minutesComboBox.addItem(i);
                }

                JPanel timePane = new JPanel(new GridLayout(0, 2));
                timePane.add(hourComboBox);
                timePane.add(minutesComboBox);

                int choice = JOptionPane.showConfirmDialog(null,
                        timePane,
                        "Hours & minutes",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (choice == JOptionPane.OK_OPTION) {
                    int hours = hourComboBox.getItemAt(hourComboBox.getSelectedIndex());
                    int minutes = minutesComboBox.getItemAt(minutesComboBox.getSelectedIndex());
                    long count = Connector.getUrbanCompany().getCountOfTransportsAtTime(LocalTime.of(hours, minutes));
                    mainLabel.setText("Count: " + count);
                }
            };
        }
    }
}
