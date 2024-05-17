package SuperObject.Items;

import MainSystem.GamePanel;
import MainSystem.UtilityTool;
import SuperObject.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    UtilityTool utilityTool;
    GamePanel gp;

    public OBJ_Door(GamePanel gp) throws IOException {
        this.gp = gp;
        name = "Door";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        collision = true;
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
    }
}
