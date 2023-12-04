package Model;

import java.util.ArrayList;

import static Utils.StaticVariables.*;

public class CollisionManager
{
    public CollisionManager(ArrayList<GameObject>[] ObjListArr)
    {
        this.ObjListArr = ObjListArr;
    }

    private ArrayList<GameObject>[] ObjListArr;

    private int tempCollidedDir = -1;
    private float tempCollidedLength = 0.f;

    public void Update(float deltaTime)
    {
        // 충돌 방향 : 박스 기준
        for (var ballObject : ObjListArr[OBJ_ENUM_BALL])
        {
            for (var brickObject : ObjListArr[OBJ_ENUM_BRICK])
            {
                // 벽돌과 공 판단
                if (Box_Circle_CollisionCheck(brickObject, ballObject))
                {
                    brickObject.OnCollision(tempCollidedDir, tempCollidedLength , ballObject);
                    int collidedDir_ball = -1;
                    switch (tempCollidedDir)
                    {
                        case DIR_NORTH:
                            collidedDir_ball = DIR_SOUTH;
                            break;
                        case DIR_SOUTH:
                            collidedDir_ball = DIR_NORTH;
                            break;
                        case DIR_EAST:
                            collidedDir_ball = DIR_WEST;
                            break;
                        case DIR_WEST:
                            collidedDir_ball = DIR_EAST;
                            break;
                    }
                    ballObject.OnCollision(collidedDir_ball, tempCollidedLength , brickObject);
                    continue;
                }
            }
            tempCollidedLength = 0.f;
            // 플레이어와 공 판단, 충돌 방향 플레이어 기준
            if (Box_Circle_CollisionCheck(ObjListArr[OBJ_ENUM_PLAYER].get(0), ballObject))
            {
                ObjListArr[OBJ_ENUM_PLAYER].get(0).OnCollision(tempCollidedDir,tempCollidedLength , ballObject);
                int collidedDir_ball = -1;
                switch (tempCollidedDir)
                {
                    case DIR_NORTH:
                        collidedDir_ball = DIR_SOUTH;
                        break;
                    case DIR_SOUTH:
                        collidedDir_ball = DIR_NORTH;
                        break;
                    case DIR_EAST:
                        collidedDir_ball = DIR_WEST;
                        break;
                    case DIR_WEST:
                        collidedDir_ball = DIR_EAST;
                        break;
                }
                ballObject.OnCollision(collidedDir_ball,tempCollidedLength , ObjListArr[OBJ_ENUM_PLAYER].get(0));
                continue;
            }
        }
        // 블럭, 볼
        // 아이템 플레이어
        tempCollidedDir = -1;
        tempCollidedLength = 0.f;
    }

    private boolean Box_Circle_CollisionCheck(GameObject box, GameObject circle)
    {
        float boxPosX = box.Get_Pos().x;
        float boxPosY = box.Get_Pos().y;
        float circlePosX = circle.Get_Pos().x;
        float circlePosY = circle.Get_Pos().y;

        float boxWidth = box.Get_Width();
        float boxHeight = box.Get_Height();
        float radius = ((Ball)circle).Get_Radius();

        float collidedWidth = Math.abs(boxPosX - circlePosX);
        float collidedHeight = Math.abs(boxPosY - circlePosY);
        // 가로 충돌 조건
        if (collidedWidth <= boxWidth + radius)
        {
            // 세로 충돌 조건
            if (collidedHeight <= boxHeight + radius)
            {
                // 충돌 확정                
                if (boxPosY < circlePosY) // 박스가 더 위
                {
                    if(collidedHeight > collidedWidth) // 세로로 겹치는 부분이 더 많다면 SOUTH
                    {
                        tempCollidedDir = DIR_SOUTH;
                        tempCollidedLength = collidedHeight;
                        return true;
                    }
                    else
                    {
                        tempCollidedDir = DIR_EAST;
                        tempCollidedLength = collidedWidth;
                        return true;
                    }
                }
                else  // 충돌 확정, 박스가 더 아래
                {
                    if(collidedHeight > collidedWidth) // 세로로 겹치는 부분이 더 많다면 NORTH
                    {
                        tempCollidedDir = DIR_NORTH;
                        tempCollidedLength = collidedHeight;
                        return true;
                    }
                    else
                    {
                        tempCollidedDir = DIR_WEST;
                        tempCollidedLength = collidedWidth;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
