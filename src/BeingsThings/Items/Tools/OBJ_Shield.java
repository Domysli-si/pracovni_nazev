package BeingsThings.Items.Tools;

import BeingsThings.Being.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Shield extends Entity {
    public OBJ_Shield(GamePanel gp) throws IOException {
        super(gp);

        name = "Normal shield";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[ " + name + " ]\n old shield.";
    }
}
