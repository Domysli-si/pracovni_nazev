package Beings_things.Aggressive_beings;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;
import java.util.Random;

public class Ag_greenSlime extends Entity {
    public Ag_greenSlime(GamePanel gp) throws IOException {
        super(gp);

        //Stats
        name = " Green slime";
        speed = 1;
        maxLife = 2;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y =18;
        solidArea.width =42;
        solidArea.height =30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    //Slime's images
    public void getImage() throws IOException {

        up1 = setup("/greenslime_down_1.png");
        up2 = setup("/greenslime_down_2.png");
        down1 = setup("/greenslime_down_1.png");
        down2 = setup("/greenslime_down_2.png");
        left1 = setup("/greenslime_down_1.png");
        left2 = setup("/greenslime_down_2.png");
        right1 = setup("/greenslime_down_1.png");
        right2 = setup("/greenslime_down_2.png");
    }

    //Set behavior of green slime
    public void setAction(){
        actionLookCounter++;
        if (actionLookCounter == 120) {
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
            actionLookCounter = 0;
        }
    }
}
