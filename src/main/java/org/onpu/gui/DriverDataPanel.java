package org.onpu.gui;

import javax.swing.*;

public class DriverDataPanel extends JPanel {
    private static final JTextField fullNameTextField = new JTextField();
    private static final JTextField livingAddressTextField = new JTextField();
    private static final JTextField phoneNumberTextField = new JTextField();
    private static final JTextField routeNameTextField = new JTextField();
    private static final JTextField startOfCareerTextField = new JTextField();
    private static final JTextField startOfRouteTextField = new JTextField();
    private static final JTextField endOfRouteTextField = new JTextField();
    private static final JTextField idTextField = new JTextField();

    public DriverDataPanel() {
        this.setLayout(null);
        this.setBounds(350, 65, 300, 285);
        componentsConfiguration();
        addComponents();
    }

    private void addComponents() {
        this.add(fullNameTextField);
        this.add(livingAddressTextField);
        this.add(phoneNumberTextField);
        this.add(routeNameTextField);
        this.add(startOfCareerTextField);
        this.add(startOfRouteTextField);
        this.add(endOfRouteTextField);
        this.add(idTextField);
    }

    private void componentsConfiguration() {
        int x = 25;
        int width = 250;
        int height = 20;

        fullNameTextField.setBounds(x, 10, width, height);
        fullNameTextField.setEnabled(false);
        fullNameTextField.setHorizontalAlignment(JTextField.CENTER);

        livingAddressTextField.setBounds(x, 40, width, height);
        livingAddressTextField.setEnabled(false);
        livingAddressTextField.setHorizontalAlignment(JTextField.CENTER);

        phoneNumberTextField.setBounds(x, 70, width, height);
        phoneNumberTextField.setEnabled(false);
        phoneNumberTextField.setHorizontalAlignment(JTextField.CENTER);

        routeNameTextField.setBounds(x, 100, width, height);
        routeNameTextField.setEnabled(false);
        routeNameTextField.setHorizontalAlignment(JTextField.CENTER);

        startOfCareerTextField.setBounds(x, 130, width, height);
        startOfCareerTextField.setEnabled(false);
        startOfCareerTextField.setHorizontalAlignment(JTextField.CENTER);

        startOfRouteTextField.setBounds(x + 75, 160, 50, height);
        startOfRouteTextField.setEnabled(false);
        startOfRouteTextField.setHorizontalAlignment(JTextField.CENTER);

        endOfRouteTextField.setBounds(x + 125, 160, 50, height);
        endOfRouteTextField.setEnabled(false);
        endOfRouteTextField.setHorizontalAlignment(JTextField.CENTER);

        idTextField.setBounds(x, 190, width, height);
        idTextField.setEnabled(false);
        idTextField.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setFullName(String t) {
        fullNameTextField.setText(t);
    }

    public void setLivingAddress(String t) {
        livingAddressTextField.setText(t);
    }

    public void setPhoneNumber(String t) {
        phoneNumberTextField.setText(t);
    }

    public void setRouteName(String t) {
        routeNameTextField.setText(t);
    }

    public void setStartOfCareer(String t) {
        startOfCareerTextField.setText(t);
    }

    public void setStartOfRoute(String t) {
        startOfRouteTextField.setText(t);
    }

    public void setEndOfRoute(String t) {
        endOfRouteTextField.setText(t);
    }

    public void setId(String t) {
        idTextField.setText(t);
    }
}
