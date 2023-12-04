package Model;

import Utils.Vector2;

import static Utils.StaticVariables.*;

public class Ball extends GameObject {
    public Ball(int x, int y, int r, Box box)
    {
        objectEnum = OBJ_ENUM_BALL;
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 50);
        radius = r;
        container = box;
    }


    private int radius;
    private Box container = null;

    public int Get_Radius() {return radius;}

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
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_PLAYER) // 부딪힌게 플레이어라면
        {
            collidedLength += EPSILON;
            switch (collidedDir)
            {
                case DIR_NORTH:
                    vVelocity.y *= -1;
                    vPos.y += collidedLength;
                    break;
                case DIR_SOUTH:
                    vVelocity.y *= -1;
                    vPos.y -= collidedLength;
                    break;
                case DIR_EAST:
                    vVelocity.x *= -1;
                    vPos.x -= collidedLength;
                    break;
                case DIR_WEST:
                    vVelocity.x *= -1;
                    vPos.x += collidedLength;
                    break;
            }

        }
    }
}