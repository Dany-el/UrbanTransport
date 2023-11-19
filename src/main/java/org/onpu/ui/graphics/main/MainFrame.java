package org.onpu.ui.graphics.main;

import javax.swing.*;
import java.awt.*;

public final class MainFrame extends JFrame {
    private MainFrame() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width - 300;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height - 500;
        this.setTitle("Urban Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.add(new MainPanel());
        this.setLocation(width / 2, height / 2);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Draws the frame
     */
    public static void drawFrame() {
        new MainFrame();
    }
}
