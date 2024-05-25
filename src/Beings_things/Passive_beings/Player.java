package Beings_things.Passive_beings;

import MainSystem.GamePanel;
import MainSystem.KeyHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    KeyHandler kH;

    //Initialize movement on single screen.
    public final int screenX;
    public final int screenY;
    int standCounter = 0;


    public Player(GamePanel gp, KeyHandler kH) throws IOException {
        super(gp);
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
        //Spawn point
        worldX = gp.tileSize *21;
        worldY = gp.tileSize *23;

        //speed
        speed = 13;
        direction = "down";

        //life stats
        maxLife = 12;
        life = maxLife;
    }

    //Loading player images
    public void getPlayerImage() throws IOException {
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
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

            //Check NPc collision
           int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
           interactNPC(npcIndex);

            //check event
            gp.eventHandler.checkEvent();

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
            spriteCounter++;
            if (spriteCounter > 12) {
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
    public void pickUpObject(int i) {

        if (i != 999) {

        }

    }

    public void interactNPC(int i) {

        if (i != 999) {
            if(gp.kH.enterPressed){
                gp.gameState = gp.dialogState;
                gp.npc[i].speak();
            }
        }
        gp.kH.enterPressed= false;
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
        int x = screenX;
        int y = screenY;

        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffSet = gp.screenWidth - screenX;
        if (rightOffSet > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffSet = gp.screenHeight - screenY;
        if (bottomOffSet > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }


        g2.drawImage(image, screenX, screenY, null);
    }
}
