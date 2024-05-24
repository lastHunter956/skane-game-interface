package main;

import jdbc.SkaneConnection;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel extends JPanel{

    CobraMenuPanel cobraMenuPanel;

    JTextField urlField, userField, passField;
    JLabel urlLabel, userLabel, passLabel;
    JButton connectButton, closeButton;

    ConnectionPanel(CobraMenuPanel cobraMenuPanel){
        this.cobraMenuPanel = cobraMenuPanel;

        setBounds(30, 30, 300, 180);
        setBackground(new Color(0, 100, 0, 255));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setLayout(null);
        setVisible(false);

        addComponents();

        cobraMenuPanel.add(this);
    }

    public void addComponents() {
        urlLabel = new JLabel("URL");
        urlLabel.setBounds(20, 20, 50, 30);
        urlLabel.setHorizontalAlignment(JLabel.CENTER);
        urlLabel.setForeground(Color.white);
        add(urlLabel);

        userLabel = new JLabel("User");
        userLabel.setBounds(20, 60, 50, 30);
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        userLabel.setForeground(Color.white);
        add(userLabel);

        passLabel = new JLabel("Pass");
        passLabel.setBounds(20, 100, 50, 30);
        passLabel.setHorizontalAlignment(JLabel.CENTER);
        passLabel.setForeground(Color.white);
        add(passLabel);

        urlField = new JTextField();
        urlField.setBounds(80, 20, 200, 30);
        urlField.setText(SkaneConnection.getUrl());
        add(urlField);

        userField = new JTextField();
        userField.setBounds(80, 60, 200, 30);
        userField.setText(SkaneConnection.getUser());
        add(userField);

        passField = new JTextField();
        passField.setBounds(80, 100, 200, 30);
        passField.setText(SkaneConnection.getPass());
        add(passField);

        connectButton = new JButton("Connect");
        connectButton.setBounds(35, 140, 100, 30);
        connectButton.addActionListener(cobraMenuPanel);
        add(connectButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(165, 140, 100, 30);
        closeButton.addActionListener(cobraMenuPanel);
        add(closeButton);
    }

}