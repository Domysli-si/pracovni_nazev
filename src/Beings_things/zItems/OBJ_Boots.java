package Beings_things.zItems;


import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Boots extends Entity {


    public OBJ_Boots(GamePanel gp) throws IOException {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);

    }
}