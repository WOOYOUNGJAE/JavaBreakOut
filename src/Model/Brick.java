package Model;

import Utils.Vector2;

import java.awt.*;

import static Utils.StaticVariables.*;

public class Brick extends GameObject
{
    public Brick(int x, int y, int cx, int cy)
    {
        objectEnum = OBJ_ENUM_BRICK;
        color = Color.WHITE;
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 0);
        width = cx; height = cy;
    }

    boolean isAlive = true;
    int hp = 1;

    public boolean Is_Alive(){return isAlive;}
    public int Get_HP() {return hp;}
    public void Set_HP(int hp) {this.hp = hp;}
    public void Set_HP(boolean plus) {if (plus) ++hp; else --hp;}

    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void OnCollision(int collidedDir, float collidedLength, GameObject collidedObject) {
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_BALL)
        {
            --hp;

            switch (hp)
            {
                case 3:
                    color = Color.BLACK;
                    break;
                case 2:
                    color = Color.GRAY;
                    break;
                case 1:
                    color = Color.white;
                    break;
                default:
                    // TODO : Error
                    break;
            }

            if (hp <= 0)
            {
                isAlive = false;
            }
        }
    }
}
