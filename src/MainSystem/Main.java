package MainSystem;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rosenrot");

        GamePanel gp = new GamePanel();
        window.add(gp);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.setupGame();
        gp.startGameThread();
    }
}