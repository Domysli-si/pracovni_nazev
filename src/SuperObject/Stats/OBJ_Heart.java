package SuperObject.Stats;

import MainSystem.GamePanel;

import SuperObject.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp)throws IOException {
        this.gp = gp;
        name = "Heart";
        image = ImageIO.read(getClass().getResourceAsStream("/stats/heart_full.png"));
        image2 = ImageIO.read(getClass().getResourceAsStream("/stats/heart_half.png"));
        image3 = ImageIO.read(getClass().getResourceAsStream("/stats/heart_blank.png"));
        image =utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        image2 =utilityTool.scaleImage(image2, gp.tileSize, gp.tileSize);
        image3 = utilityTool.scaleImage(image3 , gp.tileSize, gp.tileSize);
    }
}
