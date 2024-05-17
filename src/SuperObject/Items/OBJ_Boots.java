package SuperObject.Items;

import MainSystem.UtilityTool;
import SuperObject.SuperObject;
import MainSystem.GamePanel;


import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {
    UtilityTool utilityTool;
    GamePanel gp;

    public OBJ_Boots(GamePanel gp) throws IOException {
        this.gp = gp;
        name = "Boots";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

    }
}
