package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SkaneJDBC {

    private Connection con;

    public SkaneJDBC(Connection con){
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }


    public void addScore(Score score) {
        try {
            String searchSql = "SELECT score FROM scores WHERE nome = ?;";
            PreparedStatement searchPs = con.prepareStatement(searchSql);
            searchPs.setString(1, score.getNome());
            ResultSet rs = searchPs.executeQuery();

            if (rs.next()) {
                int existingScore = rs.getInt("score");
                if (score.getScore() > existingScore) {
                    String updateSql = "UPDATE scores SET score = ?, time = ?, speed = ?, mode = ? WHERE nome = ?;";
                    PreparedStatement updatePs = con.prepareStatement(updateSql);
                    updatePs.setInt(1, score.getScore());
                    updatePs.setTimestamp(2, Timestamp.valueOf(score.getDate()));
                    updatePs.setInt(3, score.getSpeed());
                    updatePs.setString(4, score.getMode());
                    updatePs.setString(5, score.getNome());
                    updatePs.execute();
                }
            } else {
                String insertSql = "INSERT INTO scores (nome, score, time, speed, mode) VALUES (?,?,?,?,?);";
                PreparedStatement insertPs = con.prepareStatement(insertSql);
                insertPs.setString(1, score.getNome());
                insertPs.setInt(2, score.getScore());
                insertPs.setTimestamp(3, Timestamp.valueOf(score.getDate()));
                insertPs.setInt(4, score.getSpeed());
                insertPs.setString(5, score.getMode());
                insertPs.execute();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Score> getHighscores(){
        ArrayList<Score> highscores = new ArrayList<Score>();
        String sql = "SELECT * FROM scores ORDER BY score DESC";

        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Score score = new Score();
                score.setNome(rs.getString("nome"));
                score.setScore(rs.getInt("score"));
                score.setDate(rs.getTimestamp("time").toLocalDateTime());
                score.setSpeed(rs.getInt("speed"));
                score.setMode(rs.getString("mode"));
                highscores.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highscores;
    }

}
