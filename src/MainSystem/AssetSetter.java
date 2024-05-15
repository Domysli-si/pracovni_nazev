package MainSystem;


import Entity.NPC_OldMan;

import java.io.IOException;

//Item map position
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){}
    public void setNPC() throws IOException {
        gp.npc[0]=new NPC_OldMan(gp);
gp.npc[0].worldX = gp.tileSize*21;
gp.npc[0].worldY = gp.tileSize*21;
    }


}
