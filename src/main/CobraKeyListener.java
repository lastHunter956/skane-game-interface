package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CobraKeyListener implements KeyListener{

    Cobra cobra;

    CobraKeyListener(Cobra cobra){
        this.cobra = cobra;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(!cobra.gamePanel.paused) {
            if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && cobra.timeSinceTurn > 0) {
                if(!cobra.direction.equals("down")) {
                    cobra.direction = "up";
                }
            }
            if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && cobra.timeSinceTurn > 0) {
                if(!cobra.direction.equals("right")) {
                    cobra.direction = "left";
                }
            }
            if((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && cobra.timeSinceTurn > 0) {
                if(!cobra.direction.equals("up")) {
                    cobra.direction = "down";
                }
            }
            if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && cobra.timeSinceTurn > 0) {
                if(!cobra.direction.equals("left")) {
                    cobra.direction = "right";
                }
            }
            cobra.timeSinceTurn = 0;
        }
        if(key == KeyEvent.VK_ESCAPE) {
            if(cobra.gamePanel.paused == false) {
                cobra.gamePanel.paused = true;
            }else if(cobra.gamePanel.paused == true) {
                cobra.gamePanel.paused = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}