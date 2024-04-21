package com.DPV_Vocabulary_Crafter.Client;
import javax.swing.*;

public class UIPanel {

    public void createAndShowGUI() {
        JFrame frame = new JFrame();

        JTextArea textArea = new JTextArea();
        textArea.setText("Your large text goes here...");
        textArea.setEditable(false); // Make the text area read-only

        JScrollPane scrollPane = new JScrollPane(textArea);

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
