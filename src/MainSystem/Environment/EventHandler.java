package MainSystem.Environment;

import MainSystem.GamePanel;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRectangle;
    int eventRecDefaultX, eventRecDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRectangle = new Rectangle();
        eventRectangle.x = 23;
        eventRectangle.y = 23;
        eventRectangle.width = 2;
        eventRectangle.height = 2;
        eventRecDefaultX = eventRectangle.x;
        eventRecDefaultY = eventRectangle.y;
    }

    public void checkEvent() {
        if (hit(27, 16, "right") == true) {
            damagePit(gp.dialogState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRectangle.x = eventCol * gp.tileSize + eventRectangle.x;
        eventRectangle.y = eventRow * gp.tileSize + eventRectangle.y;

        if (gp.player.solidArea.intersects(eventRectangle)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRectangle.x = eventRecDefaultX;
        eventRectangle.y = eventRecDefaultY;
        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialog = "You fall into a pit!";
        gp.player.life -= 1;

    }


}
