package Entity;

import MainSystem.GamePanel;

import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) throws IOException {
        super(gp);
        direction = "down";
        speed = 3;
        getImage();
        setDialogue();
    }

    public void getImage() throws IOException {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "Cau Maxi.";
        dialogues[1] = "Jak se mas?";
        dialogues[2] = "Pamatujes si kdy mas narozky?";
        dialogues[3] = "Jestli jo tak mi to rekni.";
    }


    //Setting movement for NPC
    public void setAction() {
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

    //For specification.
    public void speak() {
    super.speak();
    }
}
