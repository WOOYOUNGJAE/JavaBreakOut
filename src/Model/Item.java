package Model;

import Controller.CoreController;
import Utils.Vector2;

import java.awt.*;

import static Utils.StaticVariables.*;

public class Item extends GameObject
{
    public Item(int x, int y, int r, int itemType)
    {
        objectEnum = OBJ_ENUM_ITEM;
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 30);
        radius = r;
        this.itemType = itemType;
        switch (itemType)
        {
            case ITEM_TYPE_WIDTH_LONG :
                color = Color.blue;
                break;
            case ITEM_TYPE_WIDTH_SHORT:
                color = Color.MAGENTA;
                break;
            case ITEM_TYPE_BALL_SLOW:
                color = Color.GREEN;
                break;
            case ITEM_TYPE_BALL_FAST:
                color = Color.RED;
                break;
        }
    }
    private boolean isUsed = false;
    private int itemType = -1;
    private int radius;

    public int Get_Radius() {return radius;}
    public int Get_ItemType() {return itemType;}
    @Override
    public void Update(float deltaTime) {

        Move(deltaTime);
    }

    public void Move(float deltaTime)
    {
        vPos = vPos.Plus(vVelocity.Multiple(deltaTime));
    }

    @Override
    public void OnCollision(int collidedDir, float collidedLength, GameObject collidedObject) {
        if (isUsed || toBeDeleted)
        {
            return;
        }
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_PLAYER) // 부딪힌게 플레이어라면
        {
            isUsed = true;
            toBeDeleted = true;
        }
    }

}
