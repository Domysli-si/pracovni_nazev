package MainSystem;

import Beings_things.Passive_char.Entity;
import Beings_things.Passive_char.Player;
import MainSystem.Tile.TileManager;

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
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Map parameters.
    public final int maxWorldCol = 50 ;
    public final int maxWorldRow = 50 ;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    //System
    TileManager tileManager = new TileManager(this);
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
    ArrayList<Entity> entities = new ArrayList<>();

    //Game states
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;


    Thread gameThread;

    public GamePanel() throws IOException {
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true); // For better rendering performers
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void setupGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        gameState = titleState;
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {// GameLoop = core of our game
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
            }

            //Showing FPS
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    //Update player position
    public void update() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (gameState == playState) {
            //Player
            player.update();

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();

                }
            }
        }


        if (gameState == pauseState) {
            //nothing
        }


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Debug
        long drawStart = 0;
        if (kH.checkDrawTime) {
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

            entities.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entities.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entities.add(obj[i]);
                }
            }

            //Giving priority by sort methods
            Collections.sort(entities, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldX, o2.worldY);
                    return result;
                }
            });

            //Draw entities
            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).draw(g2);
            }
            //Empty list
            for (int i = 0; i < entities.size(); i++) {
                entities.remove(i);
            }

            //UI
            ui.draw(g2);
        }

        //DEBUG
        if (kH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time" + passed, 10, 400);
            System.out.println("Draw Time:" + passed);
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
