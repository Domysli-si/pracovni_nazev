package MainSystem;


import Beings_things.Aggressive_beings.Ag_greenSlime;
import Beings_things.Items.OBJ_Door;
import Beings_things.Passive_beings.NPC_OldMan;

import java.io.IOException;

//Item map position
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() throws IOException {
        //nothing

    }

    public void setNPC() throws IOException {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster() throws IOException {
        gp.mon[0]=new Ag_greenSlime(gp);
        gp.mon[0].worldX = gp.tileSize * 23;
        gp.mon[0].worldY = gp.tileSize * 36;

        gp.mon[1]=new Ag_greenSlime(gp);
        gp.mon[1].worldX = gp.tileSize * 23;
        gp.mon[1].worldY = gp.tileSize * 37;
    }
}
