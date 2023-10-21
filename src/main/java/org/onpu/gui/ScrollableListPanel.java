package org.onpu.gui;

import javax.swing.*;
import java.awt.*;

public class ScrollableListPanel extends JPanel {
    static final DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    static final JList<String> idList = new JList<>(defaultListModel);
    private static final JScrollPane scrollPane = new JScrollPane(idList);

    public ScrollableListPanel(){
        this.setLayout(new BorderLayout());
        this.setBounds(50, 100, 200, 250);
        this.add(scrollPane);
    }
}
