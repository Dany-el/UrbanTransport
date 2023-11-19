package org.onpu.ui.graphics.settings;


import org.onpu.ui.action_listener.ButtonActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SettingsWindowPanel extends JPanel {
    private JComboBox<String> listComboBox = new JComboBox<>(new String[]{"-not-selected-", "Driver", "Transport"});
    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton editButton = new JButton("Edit");
    private JButton showButton = new JButton("Show");
    private final DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private final JList<String> idList = new JList<>(defaultListModel);
    private final JScrollPane scrollPane = new JScrollPane(idList);

    public SettingsWindowPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#d6f3fa"));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addButton.addActionListener(ButtonActionListener.getAddObjectActionListener(listComboBox, defaultListModel));
        removeButton.addActionListener(ButtonActionListener.getRemoveObjectActionListener(listComboBox, idList, defaultListModel));
        editButton.addActionListener(ButtonActionListener.getEditObjectActionListener(listComboBox, idList, defaultListModel));
        showButton.addActionListener(ButtonActionListener.getShowObjectActionListener(listComboBox, idList, defaultListModel));

        listComboBox.addItemListener(e -> ButtonActionListener.updateList((String) Objects.requireNonNull(listComboBox.getSelectedItem()), defaultListModel));

        JPanel buttonPane = new JPanel(new GridLayout(1, 0));
        buttonPane.setBackground(Color.decode("#d6f3fa"));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(editButton);
        buttonPane.add(showButton);

        JPanel selectionPane = new JPanel(new GridLayout(0, 1));
        selectionPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        selectionPane.setBackground(Color.decode("#d6f3fa"));
        selectionPane.add(listComboBox);
        selectionPane.add(scrollPane);


        this.add(selectionPane, BorderLayout.NORTH);
        this.add(buttonPane, BorderLayout.SOUTH);
    }
}
