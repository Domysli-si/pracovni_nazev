package MainSystem;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //DEBUG
    boolean showDebugText = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Title state
        if (gp.gameState == gp.titleState) {
            titleState(code);
        //Select class state
        } else if (gp.gameState == gp.classSelectionState) {
            classSelectionState(code);
        }
        //Play state
        else if (gp.gameState == gp.playState) {
            try {
                playState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        //Pause state
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //dialogue state
        else if (gp.gameState == gp.dialogState) {
            dialogState(code);
        }
        //Screen state
        else if (gp.gameState == gp.statsScreenState) {
            try {
                screenStatsState(code);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void titleState(int code) {
        Movement(code);
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                classSelectionState(code);
            }
            if (gp.ui.commandNum == 1) {
                //later
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void classSelectionState(int code) {
        Movement(code);
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.player.fighter();
            }
            if (gp.ui.commandNum == 1) {
                gp.gameState = gp.playState;
                gp.player.thief();
            }
            if (gp.ui.commandNum == 2) {
                gp.gameState = gp.playState;
                gp.player.mage();
            }
            if (gp.ui.commandNum == 3) {
                titleState(code);
            }
        }
    }

    public void playState(int code) throws IOException {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.statsScreenState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }
        if (code == KeyEvent.VK_O) {
            gp.tileManager.loadMap("/maps/world01.txt");

        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void screenStatsState(int code) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(8);
            }
        }

    }

    private void Movement(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}