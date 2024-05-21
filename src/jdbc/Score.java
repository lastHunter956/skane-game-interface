package jdbc;

import java.time.LocalDateTime;

public class Score {

    private String nome;
    private int score;
    private LocalDateTime date;
    private int speed;
    private String mode;



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }



}