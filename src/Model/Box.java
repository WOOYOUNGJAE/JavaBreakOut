package Model;

import Controller.KeyboardController;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;

import static Utils.StaticVariables.OBJ_ENUM_BOX;

public class Box extends GameObject
{
    public Box(int x, int y, int cx, int cy)
    {
        objectEnum = OBJ_ENUM_BOX;
        color = Color.GRAY;
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 0);
        width = cx; height = cy;

        JFrame frame = MainFrame.Get_Instance();
        frame.addKeyListener(KeyboardController.Get_Instance());
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void Update(float deltaTime) {

    }
}
