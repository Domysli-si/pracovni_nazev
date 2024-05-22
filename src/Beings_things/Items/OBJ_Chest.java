package Beings_things.Items;

import Beings_things.Passive_char.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Chest extends Entity {


    public OBJ_Chest(GamePanel gp) throws IOException {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/key");
    }
}
