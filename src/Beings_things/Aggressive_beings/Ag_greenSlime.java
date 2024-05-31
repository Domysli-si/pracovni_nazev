package Beings_things.Aggressive_beings;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;
import java.util.Random;

public class Ag_greenSlime extends Entity {
    GamePanel gp;

    public Ag_greenSlime(GamePanel gp) throws IOException {
        super(gp);

        this.gp = gp;
        type = 2;
        //Stats
        name = " Green slime";
        speed = 1;
        maxLife = 2;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    //Slime's images
    public void getImage() throws IOException {
        up1 = setup("/monster/greenslime_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_2", gp.tileSize, gp.tileSize);
    }

    //Set behavior of green slime
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
