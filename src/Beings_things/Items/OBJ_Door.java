package Beings_things.Items;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) throws IOException {
        super(gp);

        name = "Door";
        down1 = setup("/objects/door");
    }
}
