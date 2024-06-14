package MainSystem;

import BeingsThings.Being.Entity;
import BeingsThings.Being.Player;
import MainSystem.Environment.AssetSetter;
import MainSystem.Environment.CollisionChecker;
import MainSystem.Environment.EventHandler;
import MainSystem.Environment.Sound;
import MainSystem.Tile.TileManager;
import MainSystem.UserInterface.UI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GamePanel extends JPanel implements Runnable {

    //Setting size of screen, character and scaling.
    final int originalTileSize = 16;//16x16 = size of any character in game
    final int scale = 4;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Map parameters.
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;
    private double currentFPS;

    //System
    public TileManager tileManager = new TileManager(this);
    public KeyHandler kH = new KeyHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);

    //Entity and Object
    public Player player = new Player(this, kH);
    public Entity[] obj = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] mon = new Entity[20];
    public ArrayList<Entity> entities = new ArrayList<>();

    //Game states
    public int gameState;
    public final int titleState = 0;
    public final int classSelectionState =1;
    public final int playState = 2;
    public final int pauseState = 3;
    public final int dialogState = 4;
    public final int statsScreenState = 5;

    Thread gameThread;

    public GamePanel() throws IOException {
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true); // For better rendering performers
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void setupGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // GameLoop = core of our game
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    update();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
                drawCount++;
                //Showing FPS
                if (timer >= 1000000000) {
                    currentFPS = drawCount;
                    drawCount = 0;
                    timer = 0;
                }
            }
        }
    }

    //Update player position
    public void update() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        if (gameState == playState) {
            //Player
            player.update();
            //NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            //Monster
            for (int i = 0; i < mon.length; i++) {
                if (mon[i] != null) {
                    if (mon[i].alive && !mon[i].dying) {
                        mon[i].update();
                    }
                    if (!mon[i].alive) {
                        mon[i] = null;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Debug
        long drawStart = 0;
        if (kH.showDebugText) {
            drawStart = System.nanoTime();
        }

        //Title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //others
        else {
            //MainSystem.Tile
            tileManager.draw(g2);

            //Add entities to the list.
            entities.add(player);

            for (Entity entity : npc) {
                if (entity != null) {
                    entities.add(entity);
                }
            }
            for (Entity entity : obj) {
                if (entity != null) {
                    entities.add(entity);
                }
            }
            for (Entity entity : mon) {
                if (entity != null) {
                    entities.add(entity);
                }
                
                //Giving priority by sort methods
                entities.sort(new Comparator<Entity>() {
                    @Override
                    public int compare(Entity o1, Entity o2) {
                        return Integer.compare(o1.worldX, o2.worldY);
                    }
                });

                //Draw entities
                for (Entity value : entities) {
                    value.draw(g2);
                }
                //Empty list
                entities.clear();
            }
            //UI
            ui.draw(g2);
        }

        //DEBUG
        if (kH.showDebugText) {
            long drawEnd = System.nanoTime();
            //Frame
            int frameX = tileSize / 8;
            int frameY = tileSize * 8;
            int frameWidth = tileSize * 4;
            int frameHeight = tileSize * 3;
            ui.drawSubWindow(frameX, frameY, frameWidth, frameHeight);
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            final int lineHeight = 30;
            int x = frameX + 20;
            int y = frameY + lineHeight;
            g2.drawString("FPS: " + currentFPS, x, y);
            y += lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);
            y += lineHeight;
            g2.drawString("X: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("Y: " + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;
        }
        g2.dispose();
    }


    //Constantly play sound()
    public void playMusic(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        se.setFile(i);
        se.play();
    }

}
