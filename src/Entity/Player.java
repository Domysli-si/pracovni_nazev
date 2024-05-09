package Entity;

import MainSystem.GamePanel;
import MainSystem.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler kH;

    //Initialize movement on single screen.
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler kH) throws IOException {
        this.gp = gp;
        this.kH = kH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    //global movement
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 7;
        direction = "down";
    }

    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));


    }

    public void update() {
        //Standing
        if (kH.upPressed || kH.downPressed || kH.leftPressed || kH.rightPressed) {

            //Specific keys and atributs for movement.
            if (kH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (kH.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (kH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (kH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            //Walking animation.
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
