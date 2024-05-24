package main;

import jdbc.Score;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighscoresPanel extends JPanel{

    CobraFrame frame;
    JLabel nameLabel, scoreLabel, dateLabel, speedLabel, modeLabel;

    HighscoresPanel(CobraFrame frame){
        this.frame = frame;

        setBounds(105, 120, 590, 290);
        setBackground(new Color(90, 115, 0, 255));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setLayout(null);

        fillPanel();

    }

    public void addHeader() {
        Font font = new Font("Arial", Font.BOLD, 20);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBounds(0, 0, 118, 20);
        add(nameLabel);

        scoreLabel = new JLabel("Score");
        scoreLabel.setFont(font);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(118, 0, 118, 20);
        add(scoreLabel);

        dateLabel = new JLabel("Date");
        dateLabel.setFont(font);
        dateLabel.setForeground(Color.white);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setBounds(236, 0, 118, 20);
        add(dateLabel);

        speedLabel = new JLabel("Speed");
        speedLabel.setFont(font);
        speedLabel.setForeground(Color.white);
        speedLabel.setHorizontalAlignment(JLabel.CENTER);
        speedLabel.setBounds(354, 0, 118, 20);
        add(speedLabel);

        modeLabel = new JLabel("Mode");
        modeLabel.setFont(font);
        modeLabel.setForeground(Color.white);
        modeLabel.setHorizontalAlignment(JLabel.CENTER);
        modeLabel.setBounds(472, 0, 118, 20);
        add(modeLabel);

    }

    public void fillPanel() {
        removeAll();
        addHeader();
        Font font2 = new Font("Arial", Font.BOLD, 16);

        ArrayList<Score> highscores = frame.SkaneJDBC.getHighscores();
        for(int i = 0; i < Math.min(9, highscores.size()); i++) {
            JLabel nome, score, date, speed, mode;
            nome = new JLabel(highscores.get(i).getNome());
            nome.setFont(font2);
            nome.setHorizontalAlignment(JLabel.CENTER);
            nome.setForeground(Color.BLACK);
            nome.setBounds(0, 30*i+20, 118, 30);
            add(nome);

            score = new JLabel(String.valueOf(highscores.get(i).getScore()));
            score.setFont(font2);
            score.setHorizontalAlignment(JLabel.CENTER);
            score.setForeground(Color.BLACK);
            score.setBounds(118, 30*i+20, 118, 30);
            add(score);

            String month = "";
            if(highscores.get(i).getDate().getMonthValue() < 10)month = "0";
            String formatedDate = String.valueOf(highscores.get(i).getDate().getDayOfMonth())+"/"+month+
                    String.valueOf(highscores.get(i).getDate().getMonthValue())+"/"+
                    String.valueOf(highscores.get(i).getDate().getYear());
            date = new JLabel(formatedDate);
            date.setFont(font2);
            date.setHorizontalAlignment(JLabel.CENTER);
            date.setForeground(Color.BLACK);
            date.setBounds(236, 30*i+20, 118, 30);
            add(date);

            speed = new JLabel(String.valueOf(highscores.get(i).getSpeed()));
            speed.setFont(font2);
            speed.setHorizontalAlignment(JLabel.CENTER);
            speed.setForeground(Color.BLACK);
            speed.setBounds(354, 30*i+20, 118, 30);
            add(speed);

            mode = new JLabel(highscores.get(i).getMode());
            mode.setFont(font2);
            mode.setHorizontalAlignment(JLabel.CENTER);
            mode.setForeground(Color.BLACK);
            mode.setBounds(472, 30*i+20, 118, 30);
            add(mode);
        }
    }

}