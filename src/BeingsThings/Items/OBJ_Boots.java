package BeingsThings.Items;

import BeingsThings.BeingsPassive.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Boots extends Entity {


    public OBJ_Boots(GamePanel gp) throws IOException {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);

    }
}