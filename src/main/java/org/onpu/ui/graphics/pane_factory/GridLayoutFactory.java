package org.onpu.ui.graphics.pane_factory;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public final class GridLayoutFactory {
    private static final JPanel pane = new JPanel(new GridLayout(0, 1));

    public static JPanel getPane() {
        return pane;
    }

    public static void setLayout(LayoutManager lm) {
        pane.setLayout(lm);
    }

    public static void addLabel(String t) {
        JLabel label = new JLabel(t);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        label.setHorizontalAlignment(JLabel.CENTER);
        pane.add(label);
    }

    public static JTextField addFormattedTextField(NumberFormat nf) {
        JTextField textField = new JFormattedTextField(nf);
        textField.setHorizontalAlignment(JTextField.CENTER);
        pane.add(textField);
        return textField;
    }

    public static JTextField addTextField() {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        pane.add(textField);
        return textField;
    }

    public static JTextField addTextField(String t) {
        JTextField textField = new JTextField(t);
        textField.setHorizontalAlignment(JTextField.CENTER);
        pane.add(textField);
        return textField;
    }

    public static JTextField addTextField(String t, boolean isEnabled) {
        JTextField textField = new JTextField(t);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEnabled(isEnabled);
        pane.add(textField);
        return textField;
    }

    public static <T> JComboBox<T> addComboBox(List<T> list) {
        JComboBox<T> comboBox = new JComboBox<>((T[]) list.toArray());
        pane.add(comboBox);
        return comboBox;
    }

    public static void addComponent(Component c){
        pane.add(c);
    }

    /**
     * Removes all components from panel.
     * Warning: call this method before adding new components
     */
    public static void removeComponents() {
        pane.removeAll();
    }
}
