package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CobraOptionsPanel extends JPanel implements ActionListener {

    CobraFrame frame;
    JButton backButton, increaseSpeedButton, decreaseSpeedButton, increaseModeButton, decreaseModeButton;
    JLabel changeSpeedLabel, speedLabel, changeModeLabel, modeLabel;

    Music sfx;

    CobraOptionsPanel(CobraFrame frame, Music sfx) {
        this.frame = frame;
        this.sfx = sfx;
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(92, 117, 1, 255));
        setLayout(null);

        createLabelsAndButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.remove(frame.menuPanel);
            frame.add(frame.menuPanel);
            frame.menuPanel.setVisible(true);
        }

        if (e.getSource() == increaseSpeedButton) {
            sfx.playSfx("src/main/resources/select.wav");
            if (frame.gamePanel.speed < 9) {
                frame.gamePanel.speed++;
                speedLabel.setText(String.valueOf(frame.gamePanel.speed + 1));
            }
        }

        if (e.getSource() == decreaseSpeedButton) {
            sfx.playSfx("src/main/resources/back.wav");
            if (frame.gamePanel.speed > 0) {
                frame.gamePanel.speed--;
                speedLabel.setText(String.valueOf(frame.gamePanel.speed + 1));
            }
        }

        if (e.getSource() == increaseModeButton) {
            sfx.playSfx("src/main/resources/select.wav");
            if ("free".equals(frame.gamePanel.gameMode)) {
                frame.gamePanel.gameMode = "wall";
            } else if ("wall".equals(frame.gamePanel.gameMode)) {
                frame.gamePanel.gameMode = "free";
            }
            modeLabel.setText(frame.gamePanel.gameMode);
        }

        if (e.getSource() == decreaseModeButton) {
            sfx.playSfx("src/main/resources/back.wav");
            if (frame.gamePanel.gameMode == "wall") {
                frame.gamePanel.gameMode = "free";
            } else if (frame.gamePanel.gameMode == "free") {
                frame.gamePanel.gameMode = "wall";
            }
            modeLabel.setText(frame.gamePanel.gameMode);

        }

    }

    public void createLabelsAndButtons() {
        changeSpeedLabel = new JLabel("Speed");
        Font font = new Font(changeSpeedLabel.getFont().getName(), Font.BOLD, 32);
        changeSpeedLabel.setFont(font);
        changeSpeedLabel.setForeground(Color.black);
        changeSpeedLabel.setBackground(new Color(154, 197, 3, 255));
        changeSpeedLabel.setOpaque(true);
        changeSpeedLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        changeSpeedLabel.setHorizontalAlignment(JLabel.CENTER);
        changeSpeedLabel.setBounds(50, 100, 300, 100);
        add(changeSpeedLabel);

        decreaseSpeedButton = new JButton("<");
        decreaseSpeedButton.setFont(font);
        decreaseSpeedButton.setBackground(new Color(0, 0, 0, 0));
        decreaseSpeedButton.setForeground(new Color(0, 0, 0));
        decreaseSpeedButton.setBorder(null);
        decreaseSpeedButton.setBounds(450, 100, 100, 100);
        decreaseSpeedButton.setFocusable(false);
        decreaseSpeedButton.setOpaque(false);
        decreaseSpeedButton.addActionListener(this);
        add(decreaseSpeedButton);

        speedLabel = new JLabel(String.valueOf(frame.gamePanel.speed + 1));
        speedLabel.setFont(font);
        speedLabel.setForeground(Color.black);
        speedLabel.setBackground(new Color(154, 197, 3, 255));
        speedLabel.setOpaque(true);
        speedLabel.setHorizontalAlignment(JLabel.CENTER);
        speedLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        speedLabel.setBounds(550, 100, 100, 100);
        add(speedLabel);

        increaseSpeedButton = new JButton(">");
        increaseSpeedButton.setFont(font);
        increaseSpeedButton.setBackground(new Color(0, 0, 0, 0));
        increaseSpeedButton.setForeground(new Color(0, 0, 0));
        increaseSpeedButton.setBorder(null);
        increaseSpeedButton.setBounds(650, 100, 100, 100);
        increaseSpeedButton.setFocusable(false);
        increaseSpeedButton.setOpaque(false);
        increaseSpeedButton.addActionListener(this);
        add(increaseSpeedButton);

        changeModeLabel = new JLabel("Game Mode");
        changeModeLabel.setFont(font);
        changeModeLabel.setForeground(Color.black);
        changeModeLabel.setBackground(new Color(154, 197, 3, 255));
        changeModeLabel.setOpaque(true);
        changeModeLabel.setHorizontalAlignment(JLabel.CENTER);
        changeModeLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        changeModeLabel.setBounds(50, 250, 300, 100);
        add(changeModeLabel);

        decreaseModeButton = new JButton("<");
        decreaseModeButton.setFont(font);
        decreaseModeButton.setBackground(new Color(0, 0, 0, 0));
        decreaseModeButton.setForeground(new Color(0, 0, 0));
        decreaseModeButton.setBorder(null);
        decreaseModeButton.setBounds(450, 250, 100, 100);
        decreaseModeButton.setFocusable(false);
        decreaseModeButton.setOpaque(false);
        decreaseModeButton.addActionListener(this);
        add(decreaseModeButton);

        modeLabel = new JLabel(frame.gamePanel.gameMode);
        modeLabel.setFont(font);
        modeLabel.setForeground(Color.black);
        modeLabel.setBackground(new Color(154, 197, 3, 255));
        modeLabel.setOpaque(true);
        modeLabel.setHorizontalAlignment(JLabel.CENTER);
        modeLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        modeLabel.setBounds(550, 250, 100, 100);
        add(modeLabel);

        increaseModeButton = new JButton(">");
        increaseModeButton.setFont(font);
        increaseModeButton.setBackground(new Color(0, 0, 0, 0));
        increaseModeButton.setForeground(new Color(0, 0, 0));
        increaseModeButton.setBorder(null);
        increaseModeButton.setBounds(650, 250, 100, 100);
        increaseModeButton.setFocusable(false);
        increaseModeButton.setOpaque(false);
        increaseModeButton.addActionListener(this);
        add(increaseModeButton);

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setForeground(Color.black);
        backButton.setBackground(new Color(154, 197, 3, 255));
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        backButton.setBounds(50, 400, 700, 100);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        add(backButton);
    }

}
