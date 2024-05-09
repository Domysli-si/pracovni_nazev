package Entity;

import MainSystem.GamePanel;
import MainSystem.KeyHandler;

import java.awt.*;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler kH;

    public Player(GamePanel gp, KeyHandler kH) {
        this.gp = gp;
        this.kH = kH;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 7;
    }

    public void update() {
        if (kH.upPressed) {
            y -= speed;
        } else if (kH.downPressed) {
            y += speed;
        } else if (kH.leftPressed) {
            x -= speed;
        } else if (kH.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);// Our character
    }


}
