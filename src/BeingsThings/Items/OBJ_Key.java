package BeingsThings.Items;

import BeingsThings.Being.Entity;
import MainSystem.GamePanel;
import java.io.IOException;

public class OBJ_Key extends Entity {


    public OBJ_Key(GamePanel gp)throws IOException {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[ " + name + " ]\n It opens a door.";
    }

}
