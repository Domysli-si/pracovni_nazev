package MainSystem;

import MainSystem.object.OBJ_Chest;
import MainSystem.object.OBJ_Door;
import MainSystem.object.OBJ_Key;

import java.io.IOException;


//Item map position
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() throws IOException {

        gp.superObject[0] = new OBJ_Key();
        //Item coordinates
        gp.superObject[0].worldX = 23 * gp.tileSize;
        gp.superObject[0].worldY = 7 * gp.tileSize;

        gp.superObject[1] = new OBJ_Key();
        //Item coordinates
        gp.superObject[1].worldX = 23 * gp.tileSize;
        gp.superObject[1].worldY = 40 * gp.tileSize;

        gp.superObject[2] = new OBJ_Key();
        //Item coordinates
        gp.superObject[2].worldX = 38 * gp.tileSize;
        gp.superObject[2].worldY = 8 * gp.tileSize;

        gp.superObject[3] = new OBJ_Door();
        //Item coordinates
        gp.superObject[3].worldX = 10 * gp.tileSize;
        gp.superObject[3].worldY = 11 * gp.tileSize;

        gp.superObject[4] = new OBJ_Door();
        //Item coordinates
        gp.superObject[4].worldX = 8 * gp.tileSize;
        gp.superObject[4].worldY = 28 * gp.tileSize;

        gp.superObject[5] = new OBJ_Door();
        //Item coordinates
        gp.superObject[5].worldX = 12 * gp.tileSize;
        gp.superObject[5].worldY = 22 * gp.tileSize;

        gp.superObject[6] = new OBJ_Chest();
        //Item coordinates
        gp.superObject[6].worldX = 10 * gp.tileSize;
        gp.superObject[6].worldY = 9 * gp.tileSize;


    }


}
