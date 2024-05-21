package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple {

    CobraGamePanel gamePanel;

    Random random = new Random();

    int appleX, appleY;
    int[] coordsX, coordsY;

    Apple(CobraGamePanel gamePanel, int[] coordsX, int[] coordsY){
        this.gamePanel = gamePanel;
        this.coordsX = coordsX;
        this.coordsY = coordsY;

        generateCoordinates();

    }


    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(appleX, appleY, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void generateCoordinates() {
        if(gamePanel.gameMode.equals("wall")) {
            appleX = coordsX[random.nextInt(38 - 2) + 1];
            appleY = coordsY[random.nextInt(28 - 2) + 1];
        } else {
            appleX = coordsX[random.nextInt(40 - 2) + 1];
            appleY = coordsY[random.nextInt(30 - 2) + 1];
        }
    }


}