package Model;

import Controller.KeyboardController;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;

public class Box extends GameObject
{
    public Box(int x, int y, int cx, int cy, Box box)
    {
        color = Color.GRAY;
        keyController = KeyboardController.Get_Instance();
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 0);
        width = cx; height = cy;
        container = box;

        JFrame frame = MainFrame.Get_Instance();
        frame.addKeyListener(KeyboardController.Get_Instance());
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
    }

    KeyboardController keyController = null;
    private int width;
    private int height;
    private Box container = null;

    // getter
    public int Get_Width() {return width;}
    public int Get_Height() {return height;}

    @Override
    public void Update(float deltaTime) {

        if (keyController.rightActing)
        {
            vVelocity.x = 80;
            Move(deltaTime);
        }
        else if (keyController.leftActing)
        {
            vVelocity.x = -80;
            Move(deltaTime);
        }
    }
}
