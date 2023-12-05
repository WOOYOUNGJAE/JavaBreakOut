package Model;

import Controller.KeyboardController;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;

import static Utils.StaticVariables.OBJ_ENUM_WALL;

public class Wall extends GameObject
{
    public Wall(int x, int y, int cx, int cy)
    {
        objectEnum = OBJ_ENUM_WALL;
        color = Color.lightGray;
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 0);
        width = cx; height = cy;
    }

    @Override
    public void Update(float deltaTime) {

    }
}
