package Beings_things.Items.Tools;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Shield extends Entity {
    public OBJ_Shield(GamePanel gp) throws IOException {
        super(gp);

        name = "Normal shield";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;

    }
}
