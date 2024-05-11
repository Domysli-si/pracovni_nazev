package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {
    public OBJ_Boots() throws IOException {
        name = "Boots";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
    }
}
