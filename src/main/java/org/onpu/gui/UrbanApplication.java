package org.onpu.gui;

import org.onpu.UrbanCompany;

import javax.swing.*;

public class UrbanApplication extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JLabel mainText = new JLabel("Text will be shown here");
    private JButton doSth = new JButton("Press on me");

    public UrbanApplication(){
        mainPanel.add(mainText);
        mainPanel.add(doSth);
        this.add(mainPanel);
    }

    public void start(){
        UrbanApplication jFrame = new UrbanApplication();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
