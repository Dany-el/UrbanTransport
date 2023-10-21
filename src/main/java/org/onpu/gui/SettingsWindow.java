package org.onpu.gui;

import org.onpu.UrbanCompany;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class SettingsWindow extends JFrame {
    private static SettingsWindowPanel mainPanel;
    private static boolean isOpened = false;

    public SettingsWindow(UrbanCompany urbanCompany) {
        mainPanel = new SettingsWindowPanel(this, urbanCompany);
    }

    /**
     * Returns status of the window(open or not)
     *
     * @return True if window is open, False if not
     */
    public boolean isOpened() {
        return isOpened;
    }

    /**
     * Open the settings window
     */
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
