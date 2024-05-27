package Beings_things.Stats;

import Beings_things.Passive_beings.Entity;
import MainSystem.GamePanel;

import java.io.IOException;

public class OBJ_Heart extends Entity {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) throws IOException {
        super(gp);
        name = "Heart";
        image = setup("/stats/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/stats/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/stats/heart_blank", gp.tileSize, gp.tileSize);


    }
}
