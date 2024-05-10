package MainSystem.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest()throws IOException {
        name = "Chest";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
    }
}
