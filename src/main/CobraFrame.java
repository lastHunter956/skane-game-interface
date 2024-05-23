package main;

import jdbc.SkaneConnection;
import jdbc.SkaneJDBC;

import javax.swing.*;

public class CobraFrame extends JFrame {

    CobraGamePanel gamePanel;
    CobraMenuPanel menuPanel;
    CobraOptionsPanel opsPanel;
    CobraGOPanel goPanel;
    InstructionsPanel instructionsPanel; // Asegúrate de que esta línea esté presente
    SkaneJDBC SkaneJDBC;

    Music music;
    Music sfx;

    CobraFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("La Cobrita");

        music = new Music();
        sfx = new Music();

        SkaneJDBC = new SkaneJDBC(SkaneConnection.getConnection());

        menuPanel = new CobraMenuPanel(this, music, sfx);
        goPanel = new CobraGOPanel(this, music, sfx);
        gamePanel = new CobraGamePanel(this, music, sfx);
        opsPanel = new CobraOptionsPanel(this, sfx);
        instructionsPanel = new InstructionsPanel(this, sfx);

        add(menuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void gameOver() {
        menuPanel.setVisible(false);
        goPanel.setVisible(true);
        goPanel.scoreLabel.setText("Score: " + gamePanel.score);
        remove(goPanel);
        add(goPanel);
        music.stopMusic();
        music.playSfx("src/main/resources/game_over.wav");
    }

    public void mainMenu() {
        menuPanel.setVisible(true);
        goPanel.setVisible(false);
        gamePanel.setVisible(false);
        add(menuPanel);
        music.playMusic("src/main/resources/bazooka_badger.wav");
    }

}