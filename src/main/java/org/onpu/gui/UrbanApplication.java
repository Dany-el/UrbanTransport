package org.onpu.gui;

import javax.swing.*;

public final class UrbanApplication extends JFrame {
    private static MainPanel mainPanel;

    public UrbanApplication() {
        mainPanel = new MainPanel();
    }

    /**
     * Starts the program
     */
    public void start() {
        this.setTitle("Urban Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.add(mainPanel);
        this.setResizable(false);
        this.setVisible(true);
    }
}
