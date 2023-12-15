package Model;

import Utils.Vector2;

import static Utils.StaticVariables.*;

public class Ball extends GameObject {
    public Ball(int x, int y, int r)
    {
        objectEnum = OBJ_ENUM_BALL;
        vPos = new Vector2(x, y);
//        vVelocity = new Vector2(0, 50);
        vVelocity = new Vector2(0, 100);
        radius = r;
    }



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
        collidedLength += EPSILON;
        if (collidedObject.Get_ObjEnum() == OBJ_ENUM_PLAYER) // 부딪힌게 플레이어라면
        {
            switch (collidedDir)
            {
                case DIR_NORTH:
                    vVelocity.y *= -1;
                    vPos.y += collidedLength;
                    break;
                case DIR_SOUTH:
                {
                    vPos.y -= collidedLength;
                    // 끝자락에 부딪혔을 때
                    if (Math.abs(vPos.x - collidedObject.Get_Pos().x) >= collidedObject.Get_Width()*0.4f)
                    {
                        float velocityLength = vVelocity.Length();
                        if (vPos.x > collidedObject.Get_Pos().x) // 오른쪽
                        {
                            // 우상단 45도 방향으로
                            vVelocity.x = 1.f;
                            vVelocity.y = -1.f;
                            vVelocity.Normalize();
                            vVelocity = vVelocity.Multiple(velocityLength);
                        }
                        else // 왼쪽
                        {
                            // 좌상단 45도 방향으로
                            vVelocity.x = -1.f;
                            vVelocity.y = -1.f;
                            vVelocity.Normalize();
                            vVelocity = vVelocity.Multiple(velocityLength);
                        }
                        return;
                    }
                    vVelocity.y *= -1;
                    break;
                }

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
        else if (collidedObject.Get_ObjEnum() == OBJ_ENUM_WALL || collidedObject.Get_ObjEnum() == OBJ_ENUM_BRICK) // 벽이나 벽돌과 부딪히면
        {
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