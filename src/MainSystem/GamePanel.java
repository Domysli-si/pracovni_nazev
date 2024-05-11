package MainSystem;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    //Setting size of screen, character and scaling.
    final int originalTileSize = 16;//16x16 = size of any character in game
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Map parametrs
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    //System
    TileManager tileManager = new TileManager(this);
    KeyHandler kH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    Thread gameThread;

    //Entity and Object
    public Player player = new Player(this, kH);
    public SuperObject[] superObject = new SuperObject[10];


    public GamePanel() throws IOException {
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true); // For better rendering performers
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void setupGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        assetSetter.setObject();
        playMusic(0);
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
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileManager.draw(g2);

        //Object
        for (SuperObject object : superObject) {
            if (object != null) {
                object.draw(g2, this);
            }

        }

        //Player
        player.draw(g2);

        g2.dispose();
    }

    //Constantly play sound()
    public void playMusic(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.setFile(i);
        sound.play();
    }

}
