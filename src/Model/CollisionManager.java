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
                    tempCollidedLength = 0.f;
                    tempCollidedDir = -1;
                    break;
                }
            }
            for (var wallObject : ObjListArr[OBJ_ENUM_WALL])
            {
                // 벽돌과 공 판단
                if (Box_Circle_CollisionCheck(wallObject, ballObject))
                {
                    wallObject.OnCollision(tempCollidedDir, tempCollidedLength , ballObject);
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
                    ballObject.OnCollision(collidedDir_ball, tempCollidedLength , wallObject);
                    tempCollidedLength = 0.f;
                    tempCollidedDir = -1;
                    break;
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

        float xDistance = Math.abs(boxPosX - circlePosX);
        float yDistance = Math.abs(boxPosY - circlePosY);
        float collidedLengthX = (boxWidth + radius) * 0.5f - xDistance; // 양수면 그만큼 겹침
        float collidedLengthY =  (boxHeight + radius) * 0.5f - yDistance;
        // 가로 충돌 조건
        if (collidedLengthX >= 0)
        {
            // 세로 충돌 조건
            if (collidedLengthY >= 0)
            {
                // 충돌 확정
                if (boxPosY < circlePosY) // 박스가 더 위
                {
                    if(collidedLengthX >= collidedLengthY) // 가로로 겹치는 부분이 더 많다면 SOUTH
                    {
                        tempCollidedDir = DIR_SOUTH;
                        tempCollidedLength = collidedLengthY;
                        return true;
                    }
                    else
                    {
                        tempCollidedDir = boxPosX > circlePosX ? DIR_WEST : DIR_EAST;
                        tempCollidedLength = collidedLengthX;
                        return true;
                    }
                }
                else  // 충돌 확정, 박스가 더 아래
                {
                    if(collidedLengthX >= collidedLengthY) // 가로로 겹치는 부분이 더 많다면 NORTH
                    {
                        tempCollidedDir = DIR_NORTH;
                        tempCollidedLength = collidedLengthY;
                        return true;
                    }
                    else
                    {
                        tempCollidedDir = boxPosX > circlePosX ? DIR_WEST : DIR_EAST;
                        tempCollidedLength = collidedLengthX;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
