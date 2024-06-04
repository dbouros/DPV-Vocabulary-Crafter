package com.DPV_Vocabulary_Crafter.Client;

import javax.swing.*;
import java.awt.*;

public class UIPanel {

    public void run(String title, String text) {
        // Using lambda expression.
        SwingUtilities.invokeLater(() -> { createAndShowGUI(title, text); });
    }

    public void createAndShowGUI(String title, String text) {

        Font monospacedFont = new Font(Font.MONOSPACED, Font.PLAIN, 22);

        JTextArea textArea = new JTextArea();
        textAreaConfigurations(textArea, text, monospacedFont);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JFrame frame = new JFrame();
        frameConfigurations(frame, scrollPane, title);

    }

    public void textAreaConfigurations(JTextArea textArea, String text, Font font){
        textArea.setText(text); // Set 'Text'.
        textArea.setFont(font); // Set 'Font'.
        textArea.setEditable(false); // Make the text area read-only.
        textArea.setBackground(new Color(0x1e1f22)); // Set text's background color to black (Color.BLACK) or intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).
        textArea.setForeground(new Color(0xbcbec4)); // Set text's color to white (Color.WHITE) or intellij IDEA's color: "0x1e1f22" (new Color(0xbcbec4)).

    }

    public void frameConfigurations(JFrame frame, JScrollPane scrollPane, String title){
        frame.getContentPane().add(scrollPane); // Adding the 'Scroll Panel' to the 'Frame'.
        // Set 'Background'.
        frame.getContentPane().setBackground(new Color(0x1e1f22)); // intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).
        frame.setTitle(title); // Set 'Title'.
        frame.setSize(1366, 545); // Set 'Frame's Size'.
        frame.setLocationRelativeTo(null); // Set 'Frame's Position' to center.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose 'Frame' on close.
        frame.setVisible(true); // Set the 'Frame' to visible.
    }

}
