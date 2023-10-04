package org.onpu.gui;

import org.onpu.UrbanCompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UrbanApplication extends JFrame {

    // Creating components
    private JPanel mainPanel = new JPanel();
    private JLabel mainText = new JLabel("Text will be shown here");
    private JButton method1Button = new JButton("Method 1");
    private JButton method2Button = new JButton("Method 2");
    private JButton method3Button = new JButton("Method 3");
    private JButton method4Button = new JButton("Method 4");
    private JButton method5Button = new JButton("Method 5");
    private JButton method6Button = new JButton("Method 6");
    private JButton settingsButton = new JButton("Settings");
    private SettingsWindow sw;
    private GridBagConstraints gbc = new GridBagConstraints();

    public UrbanApplication() {

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#d6f3fa"));

        mainText.setHorizontalAlignment(JLabel.CENTER);
        mainText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        // Adding action listeners to buttons
        settingsButton.addActionListener(e -> {
            if (sw == null) {
                sw = new SettingsWindow();
                sw.start();
            } else if (!sw.isOpened()) {
                sw.start();
            }
        });

        method1Button.addActionListener(e -> {
            // TODO average working time
        });

        method2Button.addActionListener(e -> {
            // TODO average length of service
        });

        method3Button.addActionListener(e -> {
            // TODO driver with greater length of service
        });

        method4Button.addActionListener(e -> {
            JFrame frameBox = new JFrame();
            frameBox.setTitle("Choosing route name");
            String routeName = JOptionPane.showInputDialog(frameBox, "Route name: ");
            // TODO specific route method
        });

        method5Button.addActionListener(e -> {
            JFrame frameBox = new JFrame();
            frameBox.setTitle("Choosing route name");
            String routeName = JOptionPane.showInputDialog(frameBox, "Route name: ");
            // TODO count of transport of specific route
        });

        method6Button.addActionListener(e -> {
            // TODO show the list of drivers
        });

        // Adding components and placing components
        mainPanel.add(mainText, stackComponents(1, 0));
        mainPanel.add(method1Button, stackComponents(1, 1));
        mainPanel.add(method2Button, stackComponents(1, 2));
        mainPanel.add(method3Button, stackComponents(1, 3));
        mainPanel.add(method4Button, stackComponents(1, 4));
        mainPanel.add(method5Button, stackComponents(1, 5));
        mainPanel.add(method6Button, stackComponents(1, 6));
        mainPanel.add(settingsButton, stackComponents(1, 7));

        this.add(mainPanel);
    }

    private GridBagConstraints stackComponents(int gridx, int gridy) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.weightx = 1.0;
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        return gbc;
    }

    public void start() {
        UrbanApplication jFrame = new UrbanApplication();
        jFrame.setTitle("Urban Application");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 500);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
