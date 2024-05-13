package Objects;

import MainSystem.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {GamePanel gp;
    public OBJ_Door(GamePanel gp) throws IOException {
        this.gp = gp;
        name = "Door";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        collision = true;
        utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
    }
}
