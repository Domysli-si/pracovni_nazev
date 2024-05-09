package MainSystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MG");

        GamePanel gP = new GamePanel();
        window.add(gP);

        window.pack();// Scaling size by our defenition

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gP.startGameThread();
    }
}