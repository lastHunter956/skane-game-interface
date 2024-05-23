package main;

import jdbc.SkaneConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CobraMenuPanel extends JPanel implements ActionListener {

    CobraFrame frame;
    JButton startButton, optionsButton, exitButton, connectionButton, instructionsButton;
    JLabel connectionLabel, playerLabel;
    ConnectionPanel connectionPanel;
    JTextField playerField;
    JScrollPane scrollPane; // Panel de desplazamiento

    JPanel contentPanel; // Panel to hold all the components

    Music music;
    Music sfx;

    CobraMenuPanel(CobraFrame frame, Music music, Music sfx) {
        this.frame = frame;
        this.music = music;
        this.sfx = sfx;
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(0, 30, 0));
        setLayout(new BorderLayout()); // Use BorderLayout

        contentPanel = new JPanel(); // Create the content panel
        contentPanel.setLayout(null); // Use null layout for the content panel
        contentPanel.setPreferredSize(new Dimension(800, 800)); // Set the preferred size
        contentPanel.setBackground(new Color(0, 30, 0)); // Set the background color
        createLabelsAndButtons();

        refreshConnection();

        music.playMusic("src/main/resources/bazooka_badger.wav");

        // Create the JScrollPane with the content panel and add it to this panel
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Add this line
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            sfx.playSfx("src/main/resources/select.wav");
            frame.gamePanel.pauseP.setVisible(false);
            setVisible(false);
            frame.add(frame.gamePanel);
            frame.gamePanel.setVisible(true);
            frame.gamePanel.requestFocus();
            frame.gamePanel.startGameThread();
        }
        if (e.getSource() == optionsButton) {
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.remove(frame.opsPanel);
            frame.add(frame.opsPanel);
            frame.opsPanel.setVisible(true);
        }
        if (e.getSource() == exitButton) {
            sfx.playSfx("src/main/resources/select.wav");
            System.exit(0);
        }
        if (e.getSource() == connectionButton) {
            sfx.playSfx("src/main/resources/select.wav");
            connectionPanel.setVisible(true);
            disableComponents();

        }
        if (e.getSource() == connectionPanel.closeButton) {
            sfx.playSfx("src/main/resources/select.wav");
            connectionPanel.setVisible(false);
            enableComponents();
        }
        if (e.getSource() == connectionPanel.connectButton) {
            sfx.playSfx("src/main/resources/select.wav");
            String url = connectionPanel.urlField.getText();
            String user = connectionPanel.userField.getText();
            String pass = connectionPanel.passField.getText();
            frame.SkaneJDBC.setCon(SkaneConnection.getConnection(url, user, pass));
            refreshConnection();
        }
        if (e.getSource() == instructionsButton) { // Asegúrate de que esta línea esté presente
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.instructionsPanel.setVisible(true);
            frame.add(frame.instructionsPanel);
        }
    }

    public void createLabelsAndButtons() {

        connectionPanel = new ConnectionPanel(this);

        playerLabel = new JLabel("Player:");
        playerLabel.setBounds(290, 10, 80, 50);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerLabel.setForeground(Color.white);
        contentPanel.add(playerLabel); // Add to contentPanel

        playerField = new JTextField("player1");
        playerField.setBounds(370, 10, 130, 50);
        playerField.setBackground(new Color(0, 51, 0, 255));
        playerField.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        playerField.setForeground(Color.white);
        playerField.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(playerField);

        startButton = new JButton("Start");
        Font font = new Font(startButton.getFont().getName(), Font.BOLD, 32);
        startButton.setFont(font);
        startButton.setForeground(Color.white);
        startButton.setBackground(new Color(0, 51, 0, 255));
        startButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        startButton.setBounds(150, 100, 500, 100);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        add(startButton);

        optionsButton = new JButton("Options");
        optionsButton.setFont(font);
        optionsButton.setForeground(Color.white);
        optionsButton.setBackground(new Color(0, 51, 0, 255));
        optionsButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        optionsButton.setBounds(150, 250, 500, 100);
        optionsButton.setFocusable(false);
        optionsButton.addActionListener(this);
        add(optionsButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(font);
        exitButton.setForeground(Color.white);
        exitButton.setBackground(new Color(0, 51, 0, 255));
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        exitButton.setBounds(150, 400, 500, 100);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        add(exitButton);

        connectionButton = new JButton("Connection");
        connectionButton.setForeground(Color.white);
        connectionButton.setBackground(new Color(0, 51, 0, 255));
        connectionButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        connectionButton.setBounds(10, 10, 100, 20);
        connectionButton.setFocusable(false);
        connectionButton.addActionListener(this);
        add(connectionButton);

        instructionsButton = new JButton("Instructions"); // Asegúrate de que esta línea esté presente
        instructionsButton.setFont(font);
        instructionsButton.setForeground(Color.white);
        instructionsButton.setBackground(new Color(0, 51, 0, 255));
        instructionsButton.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        instructionsButton.setBounds(150, 550, 500, 100);
        instructionsButton.setFocusable(false);
        instructionsButton.addActionListener(this);
        add(instructionsButton);

        connectionLabel = new JLabel();
        connectionLabel.setText(null);
        connectionLabel.setBounds(120, 10, 20, 20);
        connectionLabel.setBorder(BorderFactory.createLineBorder(new Color(102, 51, 0)));
        connectionLabel.setOpaque(true);
        add(connectionLabel);

        // Add the components to the content panel instead of this panel
        contentPanel.add(startButton);
        contentPanel.add(optionsButton);
        contentPanel.add(exitButton);
        contentPanel.add(playerLabel);
        contentPanel.add(playerField);
        contentPanel.add(connectionButton);
        contentPanel.add(instructionsButton);
        contentPanel.add(connectionLabel);

    }

    public void refreshConnection() {
        if (frame.SkaneJDBC.getCon() == null) connectionLabel.setBackground(Color.red);
        else connectionLabel.setBackground(Color.green);
    }

    public void disableComponents() {
        for (Component c : getComponents()) {
            c.setEnabled(false);
        }
    }

    public void enableComponents() {
        for (Component c : getComponents()) {
            c.setEnabled(true);
        }
    }
}