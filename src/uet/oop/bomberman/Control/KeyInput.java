package uet.oop.bomberman.Control;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean space = false;

    public void KeyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) up = true;
        else if (key == KeyEvent.VK_DOWN) down = true;
        else if (key == KeyEvent.VK_LEFT) left = true;
        else if (key == KeyEvent.VK_RIGHT) right = true;
        else if (key == KeyEvent.VK_SPACE) space = true;
        reset();
    }

    public void reset() {
        up = down = left = right = space = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
