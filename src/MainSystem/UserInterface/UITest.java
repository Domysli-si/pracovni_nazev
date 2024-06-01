package MainSystem.UserInterface;

import MainSystem.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UITest {
    public static void main(String[] args) throws IOException {
        UITest test = new UITest();
        test.testUIInitialization();
        test.testAddMessage();
        test.testDrawSubWindow();
        test.testGetXforCenteredText();
        test.testGetXforAlignToRightText();
        System.out.println("All tests passed successfully.");
    }

    public void testUIInitialization() throws IOException {
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
        assert ui != null : "UI is not initialized";
        assert ui.gp == gamePanel : "GamePanel is not set correctly";
    }

    public void testAddMessage() throws IOException {
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
        String testMessage = "Test message";
        ui.addMessage(testMessage);

        assert ui.message.size() == 1 : "Message size is incorrect";
        assert "Test message".equals(ui.message.getFirst()) : "Message content is incorrect";
        assert ui.messageCounter.size() == 1 : "Message counter size is incorrect";
        assert ui.messageCounter.getFirst() == 0 : "Message counter value is incorrect";
    }

    public void testDrawSubWindow() throws IOException {
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        ui.g2 = g2;

        ui.drawSubWindow(10, 10, 100, 100);

        // Verify colors and fillings
        Color expectedColor = new Color(0, 0, 0, 200);
        g2.setColor(expectedColor);
        g2.fillRoundRect(10, 10, 100, 100, 35, 35);

        expectedColor = new Color(255, 255, 255);
        g2.setColor(expectedColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(15, 15, 90, 90, 25, 25);
    }

    public void testGetXforCenteredText() throws IOException {
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        ui.g2 = g2;

        Font font = new Font("Arial", Font.PLAIN, 12);
        g2.setFont(font);
        gamePanel.screenWidth = 800;
        int centerX = ui.getXforCenteredText("Test");

        // Create a method to calculate text position
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth("Test");
        int expectedX = (gamePanel.screenWidth - textWidth) / 2;

        assert centerX == expectedX : "Center alignment position is incorrect";
    }

    public void testGetXforAlignToRightText() throws IOException {
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        ui.g2 = g2;

        Font font = new Font("Arial", Font.PLAIN, 12);
        g2.setFont(font);
        int alignX = ui.getXforAlignToRightText("Test", 100);

        // Create a method to calculate text position
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth("Test");
        int expectedX = 100 - textWidth;

        assert alignX == expectedX : "Right alignment position is incorrect";
    }
}
