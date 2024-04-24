package com.DPV_Vocabulary_Crafter.Client;

import javax.swing.*;
import java.awt.*;

public class UIPanel {

    public void createAndShowGUI(String title, String text) {

        Font monospacedFont = new Font(Font.MONOSPACED, Font.PLAIN, 24);

        JTextArea textArea = new JTextArea();
        textAreaConfig(textArea, text, monospacedFont);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JFrame frame = new JFrame();
        frameConfig(frame, scrollPane, title);

    }

    public void textAreaConfig(JTextArea textArea, String text, Font font){
        textArea.setText(text);
        textArea.setFont(font);
        textArea.setEditable(false); // Make the text area read-only
        textArea.setBackground(new Color(0x1e1f22)); // Set text's background color to black (Color.BLACK) or intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).
        textArea.setForeground(new Color(0xbcbec4)); // Set text's color to white (Color.WHITE) or intellij IDEA's color: "0x1e1f22" (new Color(0xbcbec4)).

    }

    public void frameConfig(JFrame frame, JScrollPane scrollPane, String title){
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().setBackground(new Color(0x1e1f22)); // intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).
        frame.setTitle(title);
        frame.setSize(1366, 545);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> { createAndShowGUI("Panel Title", "Your large text goes here... !!"); });
    }

}
