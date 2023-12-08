package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Iterator;

import static Utils.StaticVariables.*;

public class ModelController {

    private ArrayList<GameObject>[] ObjListArr;
    private CollisionManager collisionManager = null;
    private int curScene = START_SCENE;
    private int prevScene = START_SCENE;
    public void Initialize()
    {
        ObjListArr = CoreController.Get_Instance().Get_ObjListArr();
        collisionManager = new CollisionManager(ObjListArr);
    }
    public void Update(float deltaTime)
    {
        if (curScene == START_SCENE)
        {
            return;
        }
        else if (curScene == SCORE_SCENE)
        {
            return;
        }
        else if (curScene == GAME_SCENE)
        {
            for (var iter : ObjListArr)
            {
                for (var innerIter : iter)
                {
                    if (innerIter != null || innerIter.isToBeDeleted() == false) {
                        innerIter.Update(deltaTime);
                    }
                }
            }
            // Late Update
            collisionManager.Update(deltaTime);

            // 충돌이벤트까지 모두 끝나면 지워야 할 오브젝트 지우기
            for (var iter : ObjListArr)
            {
                Iterator<GameObject> iterator = iter.iterator();

                while (iterator.hasNext())
                {
                    var innerIter = iterator.next();

                    if (innerIter != null && innerIter.isToBeDeleted()) {
                        iterator.remove();  // Iterator의 remove 메서드를 사용하여 안전하게 원소를 제거
                    }
                }
            }

        }

    }

    public void Change_SceneView(int nextScene)
    {
        if (curScene == nextScene)
        {
            return;
        }
        curScene = nextScene;
        if (nextScene == GAME_SCENE)
        {
            ObjListArr[OBJ_ENUM_PLAYER].add(new Player(ScreenWidth >> 1,ScreenHeight - PlayerHeight - 40, PlayerWidth, PlayerHeight));
            ObjListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth >> 1, ScreenHeight / 40 , ScreenWidth, ScreenHeight / 20)); // North
            ObjListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth - ScreenWidth / 40, ScreenHeight>>1 , ScreenWidth / 20, ScreenHeight - ScreenHeight / 10)); // East
            ObjListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth / 40, ScreenHeight>>1 , ScreenWidth / 20, ScreenHeight - ScreenHeight / 10)); // West
            int nextLevel = CoreController.Get_Instance().Get_StartChecker().Get_Level();
            switch (nextLevel) // Add Bricks and Balls
            {
                case LEVEL_EASY:
                {
                    // Create Bricks, 4 X 4
                    int colCount = 4; // 열이 몇 개인지 (가로 벽돌 개수)
                    int rowCount = 4; // 행이 몇 개인지 (세로 벽돌 개수)
                    int brickWidth = PlayerWidth;
                    int brickHeight = PlayerHeight;
                    int firstXPos = (ScreenWidth>>1) - brickWidth * (colCount>>1) + (int)(brickWidth * 0.5f);
                    int firstYPos = (ScreenHeight>>4) + 25;
                    for (int i = 0; i < colCount * rowCount /*4X4*/; ++i)
                    {
                        Brick brick  = new Brick(firstXPos + brickWidth * (i % colCount) ,
                                firstYPos+ brickHeight * (i / colCount),
                                brickWidth, brickHeight,1 );
                        ObjListArr[OBJ_ENUM_BRICK].add(brick);

                        if (i == 8)
                        {
                            brick.Set_HP(3);
                            brick.Set_Color();
                        }
                    }
                    // Create Ball
                    for (int i = 0; i < 1; ++i)
                    {
                        ObjListArr[OBJ_ENUM_BALL].add(new Ball(ScreenWidth >> 1, // X위치 스크린 중간
                                (int)ObjListArr[OBJ_ENUM_BRICK].get(colCount * rowCount - 1).Get_Pos().y + brickWidth + 5, // y위치 최소한 제일 밑에 있는 벽돌 아래
                                BrickWidth >> 1));
                    }
                    break;
                }

                case LEVEL_NORMAL:

                    break;
                case LEVEL_HARD:

                    break;

                default: //ERROR
                    break;

            }
        }
    }

}
