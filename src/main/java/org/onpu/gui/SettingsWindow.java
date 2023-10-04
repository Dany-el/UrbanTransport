package org.onpu.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsWindow extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JButton button = new JButton("Button");
    private static boolean isOpened = false;

    // TODO add buttons and ComboBox(?) to add/delete

    public SettingsWindow() {
        button.setBounds(100, 100, 200, 200);
        mainPanel.setBackground(Color.decode("#d6f3fa"));
        mainPanel.add(button);
        this.add(mainPanel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isOpened=false;
            }
        });
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void start() {
        SettingsWindow jFrame = new SettingsWindow();
        isOpened = true;
        jFrame.setTitle("Settings");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(700, 500);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }
}
