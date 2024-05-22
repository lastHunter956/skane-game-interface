package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsPanel extends JPanel {

    private CobraFrame frame;

    public InstructionsPanel(CobraFrame frame) {
        this.frame = frame;
        setLayout(null);
        setBackground(new Color(0, 30, 0)); // Set the background color

        JLabel instructionsLabel = new JLabel("<html><center>Instrucciones del juego:<br/>"
                + "1. Usa las teclas W, A, S, D o las teclas de dirección para mover la culebra.<br/>"
                + "2. Evita chocar con las paredes y con tu propia cola.<br/>"
                + "3. Come las manzanas para ganar puntos.<br/>"
                + "4. Cada manzana que comas hará que tu culebra crezca.<br/>"
                + "5. El juego termina cuando chocas con una pared o con tu propia cola.</center></html>", SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Serif", Font.BOLD, 20)); // Make the font larger
        instructionsLabel.setForeground(Color.YELLOW); // Change the font color
        instructionsLabel.setBounds(100, 1, 600, 600);

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 20)); // Match the font with other buttons
        backButton.setForeground(Color.white); // Match the font color with other buttons
        backButton.setBackground(new Color(0, 51, 0, 255)); // Match the background color with other buttons
        backButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0))); // Match the border with other buttons
        backButton.setBounds(150, 450, 240, 50); // Adjust the size and position
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.menuPanel.setVisible(true);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Match the font with other buttons
        exitButton.setForeground(Color.white); // Match the font color with other buttons
        exitButton.setBackground(new Color(0, 51, 0, 255)); // Match the background color with other buttons
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0))); // Match the border with other buttons
        exitButton.setBounds(410, 450, 240, 50); // Adjust the size and position
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(instructionsLabel);
        add(backButton);
        add(exitButton);
    }
}