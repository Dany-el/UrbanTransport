package org.onpu.gui;

import javax.swing.*;

public class TransportDataPanel extends JPanel {
    private static final JTextField driverFullNameTextField = new JTextField();
    private static final JTextField driverIdTextField = new JTextField();
    private static final JTextField typeTextField = new JTextField();
    private static final JTextField routeNameTextField = new JTextField();
    private static final JTextField numberTextField = new JTextField();
    private static final JTextField idTextField = new JTextField();

    public TransportDataPanel() {
        this.setLayout(null);
        this.setBounds(350, 65, 300, 285);
        componentsConfiguration();
        addComponents();
    }

    private void addComponents() {
        this.add(driverFullNameTextField);
        this.add(driverIdTextField);
        this.add(typeTextField);
        this.add(routeNameTextField);
        this.add(numberTextField);
        this.add(idTextField);
    }

    private void componentsConfiguration() {
        int x = 25;
        int width = 250;
        int height = 20;

        driverFullNameTextField.setBounds(x, 10, width, height);
        driverFullNameTextField.setEnabled(false);
        driverFullNameTextField.setHorizontalAlignment(JTextField.CENTER);

        driverIdTextField.setBounds(x, 40, width, height);
        driverIdTextField.setEnabled(false);
        driverIdTextField.setHorizontalAlignment(JTextField.CENTER);

        typeTextField.setBounds(x, 70, width, height);
        typeTextField.setEnabled(false);
        typeTextField.setHorizontalAlignment(JTextField.CENTER);

        routeNameTextField.setBounds(x, 100, width, height);
        routeNameTextField.setEnabled(false);
        routeNameTextField.setHorizontalAlignment(JTextField.CENTER);

        numberTextField.setBounds(x, 130, width, height);
        numberTextField.setEnabled(false);
        numberTextField.setHorizontalAlignment(JTextField.CENTER);

        idTextField.setBounds(x, 160, width, height);
        idTextField.setEnabled(false);
        idTextField.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setDriverFullName(String t) {
        driverFullNameTextField.setText(t);
    }

    public void setDriverId(String t) {
        driverIdTextField.setText(t);
    }

    public void setType(String t) {
        typeTextField.setText(t);
        typeTextField.setText(t);
    }

    public void setNumber(String t) {
        numberTextField.setText(t);
    }

    public void setRouteName(String t) {
        routeNameTextField.setText(t);
    }

    public void setId(String t) {
        idTextField.setText(t);
    }
}
