package Controller;

import Model.*;

import java.util.ArrayList;

import static Utils.StaticVariables.*;

public class ModelController {

    private ArrayList<GameObject>[] ObjListArr;
    private CollisionManager collisionManager = null;
    private int curScene = START_SCENE;
    private int prevScene = START_SCENE;
    private Box box = new Box(ScreenWidth>>1, ScreenHeight>>1, ScreenWidth, ScreenHeight );
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
                    if (innerIter != null) {
                        innerIter.Update(deltaTime);
                    }
                }
            }
            // Late Update
            collisionManager.Update(deltaTime);
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
            ObjListArr[OBJ_ENUM_PLAYER].add(new Player(ScreenWidth >> 1,ScreenHeight >> 1, PlayerWidth, PlayerHeight, box));
            ObjListArr[OBJ_ENUM_BOX].add(box);
            int nextLevel = CoreController.Get_Instance().Get_StartChecker().Get_Level();
            switch (nextLevel)
            {
                case LEVEL_EASY:
                {
                    for (int i = 0; i < 1; ++i)
                    {
                        ObjListArr[OBJ_ENUM_BALL].add(new Ball(ScreenWidth >> 1, 0 * ScreenHeight >> 1, BrickWidth >> 1, box));
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
