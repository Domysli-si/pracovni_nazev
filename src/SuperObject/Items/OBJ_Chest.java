package SuperObject.Items;

import MainSystem.GamePanel;
import SuperObject.SuperObject;
import MainSystem.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    UtilityTool utilityTool;
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) throws IOException {
        this.gp = gp;
        name = "Chest";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
    }
}
