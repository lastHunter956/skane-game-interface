package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CobraKeyListener implements KeyListener{

    Cobra cobra;

    CobraKeyListener(Cobra cobra){
        this.cobra = cobra;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!cobra.gamePanel.paused) {
            if(c == 'w' && cobra.timeSinceTurn > 0) {
                if(cobra.direction != "down") {
                    cobra.direction = "up";
                }
            }
            if(c == 'a' && cobra.timeSinceTurn > 0) {
                if(cobra.direction != "right") {
                    cobra.direction = "left";
                }
            }
            if(c == 's' && cobra.timeSinceTurn > 0) {
                if(cobra.direction != "up") {
                    cobra.direction = "down";
                }
            }
            if(c == 'd' && cobra.timeSinceTurn > 0) {
                if(cobra.direction != "left") {
                    cobra.direction = "right";
                }
            }
            cobra.timeSinceTurn = 0;
        }

        if(c == '\n' || c == '') {
            if(cobra.gamePanel.paused == false) {
                cobra.gamePanel.paused = true;
            }else if(cobra.gamePanel.paused == true) {
                cobra.gamePanel.paused = false;
            }
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}