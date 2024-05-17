package SuperObject.Items;

import MainSystem.GamePanel;
import MainSystem.UtilityTool;
import SuperObject.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    UtilityTool utilityTool;
    GamePanel gp;

    public OBJ_Key(GamePanel gp)throws IOException {
        this.gp = gp;
        name = "Key";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
    }

}
