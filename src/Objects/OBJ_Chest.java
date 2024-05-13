package Objects;

import MainSystem.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{    GamePanel gp;
    public OBJ_Chest(GamePanel gp)throws IOException {
        this.gp =gp;
        name = "Chest";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
    }
}
