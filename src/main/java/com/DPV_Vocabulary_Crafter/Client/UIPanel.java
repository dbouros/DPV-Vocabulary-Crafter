package com.DPV_Vocabulary_Crafter.Client;
import javax.swing.*;
import java.awt.*;

public class UIPanel {

    public void createAndShowGUI() {
        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea();
        Font customFont = new Font(Font.MONOSPACED, Font.PLAIN, 24);
        JScrollPane scrollPane;

        textArea.setText("Your large text goes here...");
        textArea.setFont(customFont);
        textArea.setEditable(false); // Make the text area read-only
        textArea.setBackground(new Color(0x1e1f22)); // Set background color to black (Color.BLACK) or intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).
        textArea.setForeground(new Color(0xbcbec4)); // Set text color to white (Color.WHITE).

        scrollPane = new JScrollPane(textArea);

        // Get the content pane of the frame and set its background color to black
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(0x1e1f22)); // intellij IDEA's color: "0x1e1f22" (new Color(0x1e1f22)).

        frame.getContentPane().add(scrollPane);

        frame.setTitle("Large Text Display");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

}
