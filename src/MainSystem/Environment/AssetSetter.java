package MainSystem.Environment;


import BeingsThings.BeingsAggressive.Ag_greenSlime;
import BeingsThings.BeingsPassive.NPC_OldMan;
import MainSystem.GamePanel;

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
        int i = 0;
        gp.mon[i] = new Ag_greenSlime(gp);
        gp.mon[i].worldX = gp.tileSize * 22;
        gp.mon[i].worldY = gp.tileSize * 22;
        i++;
        gp.mon[i] = new Ag_greenSlime(gp);
        gp.mon[i].worldX = gp.tileSize * 23;
        gp.mon[i].worldY = gp.tileSize * 23;
        i++;
        gp.mon[i] = new Ag_greenSlime(gp);
        gp.mon[i].worldX = gp.tileSize * 24;
        gp.mon[i].worldY = gp.tileSize * 24;
        i++;
        gp.mon[i] = new Ag_greenSlime(gp);
        gp.mon[i].worldX = gp.tileSize * 21;
        gp.mon[i].worldY = gp.tileSize * 21;
        i++;

    }
}
