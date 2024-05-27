package Beings_things.Items;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;
import java.io.IOException;

public class OBJ_Key extends Entity {


    public OBJ_Key(GamePanel gp)throws IOException {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
    }

}
