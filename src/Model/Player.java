package Model;

import Controller.CoreController;
import Controller.KeyboardController;
import Controller.ModelController;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;

import static Utils.StaticVariables.*;

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

    @Override
    public void OnCollision(int collidedDir, float collidedLength, GameObject collidedObject) {
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_ITEM)
        {
            switch (((Item)collidedObject).Get_ItemType())
            {
                case ITEM_TYPE_WIDTH_LONG : // 파란색
                    width *= 1.5f;
                    break;
                case ITEM_TYPE_WIDTH_SHORT: // 마젠타, 핑크
                    width *= 0.5f;
                    break;
                case ITEM_TYPE_BALL_SLOW: // 초록색
                    CoreController.Get_Instance().Change_BallSpeed(true);
                    break;
                case ITEM_TYPE_BALL_FAST: // 빨간색
                    CoreController.Get_Instance().Change_BallSpeed(false);
                    break;
            }

        }
    }
}
