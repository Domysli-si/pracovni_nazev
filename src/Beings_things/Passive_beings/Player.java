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

        //size of weapon's hit box
        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    //Movement
    public void setDefaultValues() {
        //Spawn point
        worldX = gp.tileSize * 21;
        worldY = gp.tileSize * 23;

        //speed
        speed = 13;
        direction = "down";

        //life stats
        maxLife = 12;
        life = maxLife;
    }

    //Loading player images
    public void getPlayerImage() throws IOException {
        up1 = setup("/player/movement/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/movement/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/movement/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/movement/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/movement/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/movement/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/movement/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/movement/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() throws IOException {
        attackUp1 = setup("/player/attack/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/attack/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/attack/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/attack/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/attack/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/attack/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/attack/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/attack/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }


    public void update() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        if (attacking) {
            attacking();
        } else if (kH.upPressed || kH.downPressed || kH.leftPressed || kH.rightPressed || kH.enterPressed) { //Standing

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

            //Check NPC collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Check monster collision
            int monIndex = gp.collisionChecker.checkEntity(this, gp.mon);
            contactMonster(monIndex);

            //check event
            gp.eventHandler.checkEvent();

            //collision is false => player can move
            if (!collisionOn && !kH.enterPressed) {
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
            gp.eventHandler.checkEvent();

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNumber = 1;
                standCounter = 0;
            }
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;

            }
        }
    }

    //Setting length of attacking animation
    public void attacking() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNumber = 2;


            //setting weapon hit box
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monIndex = gp.collisionChecker.checkEntity(this, gp.mon);
            damageMon(monIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }


    //Choosing what happens with item if player touches it.
    public void pickUpObject(int i) {

        if (i != 999) {

        }

    }

    public void interactNPC(int i) {

        if (gp.kH.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogState;
                gp.npc[i].speak();
            } else {
                //gp.playSE(7);
                attacking = true;
            }
        }

        gp.kH.enterPressed = false;
    }

    public void contactMonster(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (i != 999) {
            if (!invincible) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMon(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (i != 999) {
            if (!gp.mon[i].invincible) {
                gp.playSE(5);
                gp.mon[i].life -= 1;
                gp.mon[i].invincible = true;
                gp.mon[i].damageReaction();
                if (gp.mon[i].life <= 0) {
                    gp.mon[i].dying = true;
                }

            }
        }
    }

    //draw player's movement
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) {
                        image = attackUp1;
                    }
                    if (spriteNumber == 2) {
                        image = attackUp2;
                    }
                }

                break;
            case "down":
                if (!attacking) {
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                }
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackDown1;
                    }
                    if (spriteNumber == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNumber == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                }
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackRight1;
                    }
                    if (spriteNumber == 2) {
                        image = attackRight2;
                    }
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


        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


       /* //DEBUGGING for invincibleCounter
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("invincible: " + invincibleCounter, 10, 400);
    */
    }
}
