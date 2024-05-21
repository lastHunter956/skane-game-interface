package main;

import java.awt.*;



public class Cobra {

    CobraGamePanel gamePanel;
    int[] coordsX, coordsY;

    int x, y;
    int cobraX, cobraY;
    int timeSinceTurn;
    public String direction;

    int [][] cobraTiles;
    int[][] cobraTilesCopy;

    Cobra(CobraGamePanel gamePanel, int[] coordsX, int[] coordsY){

        this.gamePanel = gamePanel;
        this.coordsX = coordsX;
        this.coordsY = coordsY;
        setDefaultValues();

    }

    public void setDefaultValues() {
        direction = "down";
        timeSinceTurn = 0;
        x = 3;
        y = 3;
        cobraX = coordsX[x];
        cobraY = coordsY[y];

        cobraTiles = new int[3][2];
        int[] posZero = {cobraX, cobraY};
        int[] posOne = {coordsX[x], coordsY[y-1]};
        int[] posTwo = {coordsX[x], coordsY[y-2]};
        cobraTiles[0] = posZero;
        cobraTiles[1] = posOne;
        cobraTiles[2] = posTwo;

    }

    public void update() {
        if(gamePanel.paused) {

        } else {
            switch(direction) {
                case "up":
                    y--;
                    if(y < 0) {
                        if(gamePanel.gameMode.equals("wall")) {
                            gamePanel.gameOver();
                        } else {
                            y = 29;
                        }
                    }
                    break;
                case "down":
                    y++;
                    if(y > 29) {
                        if(gamePanel.gameMode.equals("wall")) {
                            gamePanel.gameOver();

                        } else {
                            y = 0;
                        }
                    }
                    break;
                case "left":
                    x--;
                    if(x < 0) {
                        if(gamePanel.gameMode.equals("wall")) {
                            gamePanel.gameOver();
                        } else {
                            x = 39;
                        }
                    }
                    break;
                case "right":
                    x++;
                    if(x > 39) {
                        if(gamePanel.gameMode.equals("wall")) {
                            gamePanel.gameOver();
                        } else {
                            x = 0;
                        }
                    }
                    break;
            }
            cobraX = coordsX[x];
            cobraY = coordsY[y];
            int[] currentTile = {cobraX, cobraY};
            checkCollision(currentTile);
            slither(currentTile);

            // apple eaten
            if(cobraX == gamePanel.apple.appleX && cobraY == gamePanel.apple.appleY) {
                gamePanel.score();
                grow(currentTile);
            }
        }
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        for(int[] tile : cobraTiles) {
            g.fillRect(tile[0], tile[1], gamePanel.tileSize, gamePanel.tileSize);
        }

    }

    public void slither(int[] currentTile) {
        for(int i = 1; i < cobraTiles.length; i++) {
            cobraTiles[cobraTiles.length-i] = cobraTiles[cobraTiles.length-i-1];
        }
        cobraTiles[0] = currentTile;
    }


    public void grow(int[] currentTile) {
        cobraTilesCopy = cobraTiles;
        cobraTiles = new int[cobraTilesCopy.length+1][2];
        for(int i = 0; i < cobraTilesCopy.length; i++) {
            cobraTiles[i][0] = cobraTilesCopy[i][0];
            cobraTiles[i][1] = cobraTilesCopy[i][1];
        }
        cobraTiles[cobraTiles.length-1][0] = currentTile[0];
        cobraTiles[cobraTiles.length-1][1] = currentTile[1];

    }

    public void checkCollision(int[] currentTile) {
        for(int i = 0; i < cobraTiles.length; i++) {
            if(cobraTiles[i][0] == currentTile[0] && cobraTiles[i][1] == currentTile[1]) {
                gamePanel.gameOver();
            }
        }
    }


}