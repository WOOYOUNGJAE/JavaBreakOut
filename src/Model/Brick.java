package Model;

import Utils.Vector2;

import java.awt.*;

import static Utils.StaticVariables.*;

public class Brick extends GameObject
{
    public Brick(int x, int y, int cx, int cy, int startHP)
    {
        hp = startHP;
        Set_Color(); //startHP에 맞게 색 설정
        objectEnum = OBJ_ENUM_BRICK;
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
    public void Set_Color()
    {
        switch (hp) {
            case 3:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.GRAY;
                break;
            case 1:
                color = Color.BLACK;
                break;
            default:
                // TODO : Error
                break;
        }
    }
    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void OnCollision(int collidedDir, float collidedLength, GameObject collidedObject) {
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_BALL)
        {
            --hp;

            Set_Color();

            if (hp <= 0)
            {
                isAlive = false;
            }
        }
    }
}
