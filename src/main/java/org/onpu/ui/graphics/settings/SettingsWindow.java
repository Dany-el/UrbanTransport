package org.onpu.ui.graphics.settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class SettingsWindow extends JFrame {
    private static boolean isOpened = false;

    private SettingsWindow() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width - 450;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height - 350;
        isOpened = true;
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(width / 2, height / 2);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isOpened = false;
            }
        });
        this.add(new SettingsWindowPanel());
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }

    public static boolean isOpened() {
        return isOpened;
    }

    public static void drawFrame() {
        new SettingsWindow();
    }
}
