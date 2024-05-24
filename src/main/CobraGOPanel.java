package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CobraGOPanel extends JPanel implements ActionListener {

    CobraFrame frame;
    JLabel gameOverLabel, scoreLabel;
    JButton restartButton, mainMenuButton, exitButton;
    HighscoresPanel highscoresPanel;

    Music music;
    Music sfx;

    CobraGOPanel(CobraFrame frame, Music music, Music sfx) {
        this.frame = frame;
        this.music = music;
        this.sfx = sfx;
        setPreferredSize(new Dimension(800, 600));
        setBounds(0, 0, 800, 600);
        setBackground(new Color(154, 197, 3, 255));
        setLayout(null);

        createButtons();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            sfx.playSfx("src/main/resources/select.wav");
            setVisible(false);
            frame.remove(frame.gamePanel);
            frame.add(frame.gamePanel);
            frame.gamePanel.setVisible(true);
            frame.gamePanel.requestFocus();
            frame.gamePanel.startGameThread();
        }
        if (e.getSource() == mainMenuButton) {
            sfx.playSfx("src/main/resources/select.wav");
            frame.mainMenu();
        }
        if (e.getSource() == exitButton) {
            sfx.playSfx("src/main/resources/select.wav");
            System.exit(0);
        }
    }

    public void createButtons() {
        gameOverLabel = new JLabel("Game Over!");
        Font font = new Font(gameOverLabel.getFont().getName(), Font.BOLD, 32);
        Font font2 = new Font(gameOverLabel.getFont().getName(), Font.BOLD, 52);
        gameOverLabel.setFont(font2);
        gameOverLabel.setForeground(Color.black);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setBounds(150, 0, 500, 60);
        add(gameOverLabel);

        scoreLabel = new JLabel();
        scoreLabel.setFont(font2);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(150, 60, 500, 60);
        add(scoreLabel);

        highscoresPanel = new HighscoresPanel(frame);
        add(highscoresPanel);

        restartButton = new JButton("Restart");
        restartButton.setFont(font);
        restartButton.setForeground(Color.black);
        restartButton.setBackground(new Color(94, 121, 1, 255));
        restartButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        restartButton.setBounds(80, 420, 200, 150);
        restartButton.setFocusable(false);
        restartButton.addActionListener(this);
        add(restartButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(font);
        mainMenuButton.setForeground(Color.black);
        mainMenuButton.setBackground(new Color(94, 121, 1, 255));
        mainMenuButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        mainMenuButton.setBounds(300, 420, 200, 150);
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(this);
        add(mainMenuButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(font);
        exitButton.setForeground(Color.black);
        exitButton.setBackground(new Color(94, 121, 1, 255));
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        exitButton.setBounds(520, 420, 200, 150);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        add(exitButton);
    }


}