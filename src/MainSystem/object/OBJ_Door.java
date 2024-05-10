package MainSystem.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() throws IOException {
        name = "Door";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        collision = true;
    }
}
