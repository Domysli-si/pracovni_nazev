package MainSystem.Tile;

import MainSystem.GamePanel;
import MainSystem.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    //Looking for files that are used in background.
    public void getTileImage() throws IOException {
        setup(0, "grass01", false);
        setup(1, "wall", true);
        setup(2, "water00", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
    }

    //Sett up path to files.
    public void setup(int index, String imageName, boolean collision) throws IOException {
        UtilityTool utilityTool = new UtilityTool();
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
        tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        tile[index].collision = collision;

    }

    public void loadMap(String filePath) throws IOException {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            String line = br.readLine();

            while (col < gp.maxWorldCol) {
                String[] numbers = line.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
                col++;
            }
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
        br.close();
    }

    //Drawing map
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //Stop moving camera on the edge.
            //left side
            if (gp.player.screenX > gp.player.worldX) {
                screenX = worldX;
            }
            //top side
            if (gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            //right side
            int rightOffSet = gp.screenWidth - gp.player.screenX;
            if (rightOffSet > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            //right side
            int bottomOffSet = gp.screenHeight - gp.player.screenY;
            if (bottomOffSet > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            } else if (gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffSet > gp.worldWidth - gp.player.worldX ||
                    bottomOffSet > gp.worldHeight - gp.player.worldY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
