package Entity;

import MainSystem.GamePanel;
import MainSystem.KeyHandler;
import MainSystem.UtilityTool;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler kH;

    //Initialize movement on single screen.
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    public Player(GamePanel gp, KeyHandler kH) throws IOException {
        this.gp = gp;
        this.kH = kH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //Setting size of terrain collision
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    //Movement
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 7;
        direction = "down";
    }


    //Loading player images
    public void getPlayerImage() throws IOException {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }


    public BufferedImage setup(String imageName) throws IOException {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        image = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
        image = utilityTool.scaleImage(image, gp.tileSize,gp.tileSize);
        return image;
    }

    public void update() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Standing
        if (kH.upPressed || kH.downPressed || kH.leftPressed || kH.rightPressed) {

            //Specific keys and attributes for movement.
            if (kH.upPressed) {
                direction = "up";
            } else if (kH.downPressed) {
                direction = "down";
            } else if (kH.leftPressed) {
                direction = "left";
            } else if (kH.rightPressed) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //Check object collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //collision is false => player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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

    //Choosing what happens with item if player touches it.
    public void pickUpObject(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (i != 999) {
            String objectName = gp.superObject[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.superObject[i] = null;
                    gp.ui.showMessage("You got a key. ");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.superObject[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door. ");
                    } else {
                        gp.ui.showMessage("You need a key.");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed *= 2;
                    gp.superObject[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }

    }

    //draw player's movement
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
