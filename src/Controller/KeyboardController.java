package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyboardController implements KeyListener {
public static KeyboardController instance = null;

    public static KeyboardController Get_Instance()
    {
        if (instance == null)
        {
            instance = new KeyboardController();
        }
        return instance;
    }


    public boolean leftActing = false;
    public boolean rightActing = false;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftActing = true;
            rightActing = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightActing = true;
            leftActing = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftActing = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightActing = false;
        }
    }
}
