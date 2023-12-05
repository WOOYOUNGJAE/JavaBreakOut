package Model;

import Controller.KeyboardController;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;

import static Utils.StaticVariables.OBJ_ENUM_PLAYER;

public class Player extends GameObject
{
    public Player(int x, int y, int cx, int cy)
    {
        objectEnum = OBJ_ENUM_PLAYER;
        color = Color.GRAY;
        keyController = KeyboardController.Get_Instance();
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 0);
        width = cx; height = cy;

        JFrame frame = MainFrame.Get_Instance();
        frame.addKeyListener(KeyboardController.Get_Instance());
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
    }

    KeyboardController keyController = null;


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

    public void Move(float deltaTime)
    {
        vPos = vPos.Plus(vVelocity.Multiple(deltaTime));
    }

}
