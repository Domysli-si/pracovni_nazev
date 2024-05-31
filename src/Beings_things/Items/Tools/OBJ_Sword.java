package Beings_things.Items.Tools;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Sword extends Entity {
    public OBJ_Sword(GamePanel gp) throws IOException {
        super(gp);

        name = "Normal sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[ " + name + " ]\n old sword.";

    }
}
