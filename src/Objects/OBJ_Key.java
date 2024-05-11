package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key()throws IOException {
        name = "Key";
        image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
    }

}
