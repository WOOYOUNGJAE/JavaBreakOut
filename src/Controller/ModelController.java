package Controller;

import Model.*;
import MyGUI.Frame.MainFrame;
import Utils.Vector2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

import static Utils.StaticVariables.*;

public class ModelController {

    private ArrayList<GameObject>[] objListArr;
    private CollisionManager collisionManager = null;
    private int curScene = START_SCENE;
    private int prevScene = START_SCENE;
    private boolean gameCleared = false;
    public void Initialize()
    {
        objListArr = CoreController.Get_Instance().Get_ObjListArr();
        collisionManager = new CollisionManager(objListArr);
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
            for (var iter : objListArr)
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
            for (var iter : objListArr)
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

            // 게임 오버 체크
            // 화면보다 밑에 있으면
            if (objListArr[OBJ_ENUM_BALL].get(0).Get_Pos().y > ScreenHeight + 10 && CoreController.Get_Instance().deltaTime != 0.f) {
                CoreController.Get_Instance().deltaTime = 0.f;
                JOptionPane.showMessageDialog(MainFrame.Get_Instance(),
                        "Game Over");
                CoreController.Get_Instance().Change_NextScene(START_SCENE);
                return;
            }


            // 모든 벽돌 깨짐
            if (objListArr[OBJ_ENUM_BRICK].isEmpty() && gameCleared == false && GameManager.Get_Instance().accTime > 2/*예외처리 : 벽돌이 생성되기 이전에 승리판정 하는 상황 방지 위해 2초 여유시간*/)
            {
                gameCleared = true;

                CoreController.Get_Instance().deltaTime = 0.f;

                String strLevel = "";
                int curLevel = CoreController.Get_Instance().Get_StartChecker().Get_Level();
                switch (curLevel)
                {
                    case LEVEL_EASY -> strLevel = "Easy";
                    case LEVEL_NORMAL -> strLevel = "Normal";
                    case LEVEL_HARD -> strLevel = "Hard";
                }
                int timeLeft = (300 - (int)GameManager.Get_Instance().accTime / 1000);
                int score = timeLeft * curLevel;
                JOptionPane.showMessageDialog(MainFrame.Get_Instance(),
                        "Clear\nTime Left : " + timeLeft + "\n"
                                + "Level : " + strLevel + "\n"
                                + "Score : Time Left * Level\n  " + score);
                UserManager.Get_Instance().curUser.Set_Score(score);
                CoreController.Get_Instance().Change_NextScene(START_SCENE);
                gameCleared = false;
            }

            // 시간 세기
            GameManager.Get_Instance().Update();
        }

    }

    public void Change_SceneView(int nextScene)
    {
        if (curScene == nextScene)
        {
            return;
        }
        curScene = nextScene;
        if (nextScene == START_SCENE)
        {
            for (var iter : objListArr)
            {
                Iterator<GameObject> iterator = iter.iterator();

                while (iterator.hasNext())
                {
                    var innerIter = iterator.next();

                    if (innerIter != null) {
                        iterator.remove();
                    }
                }
            }
        }
        else if (nextScene == GAME_SCENE)
        {
            CoreController.Get_Instance().deltaTime = 0.1f;
            objListArr[OBJ_ENUM_PLAYER].add(new Player(ScreenWidth >> 1,ScreenHeight - PlayerHeight - 40, PlayerWidth, PlayerHeight));
            objListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth >> 1, ScreenHeight / 40 , ScreenWidth, ScreenHeight / 20)); // North
            objListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth - ScreenWidth / 40, ScreenHeight>>1 , ScreenWidth / 20, ScreenHeight - ScreenHeight / 10)); // East
            objListArr[OBJ_ENUM_WALL].add(new Wall(ScreenWidth / 40, ScreenHeight>>1 , ScreenWidth / 20, ScreenHeight - ScreenHeight / 10)); // West
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
                        objListArr[OBJ_ENUM_BRICK].add(brick);

                    }
                    // Create Ball
                    for (int i = 0; i < 1; ++i)
                    {
                        Ball newBall = new Ball(ScreenWidth >> 1, // X위치 스크린 중간
                                (int) objListArr[OBJ_ENUM_BRICK].get(colCount * rowCount - 1).Get_Pos().y + brickWidth + 5, // y위치 최소한 제일 밑에 있는 벽돌 아래
                                BrickWidth >> 1);
                        objListArr[OBJ_ENUM_BALL].add(newBall);
                        newBall.Set_Velocity(new Vector2(0, 50));
                    }
                    break;
                }

                case LEVEL_NORMAL:
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
                        objListArr[OBJ_ENUM_BRICK].add(brick);

                    }
                    // Create Ball
                    for (int i = 0; i < 1; ++i)
                    {
                        Ball newBall = new Ball(ScreenWidth >> 1, // X위치 스크린 중간
                                (int) objListArr[OBJ_ENUM_BRICK].get(colCount * rowCount - 1).Get_Pos().y + brickWidth + 5, // y위치 최소한 제일 밑에 있는 벽돌 아래
                                BrickWidth >> 1);
                        objListArr[OBJ_ENUM_BALL].add(newBall);
                        newBall.Set_Velocity(new Vector2(0, 90));
                    }
                    break;
                }
                case LEVEL_HARD:
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
                        objListArr[OBJ_ENUM_BRICK].add(brick);

                        if (i == 0 || i == 2 || i == 5 || i == 7 || i == 8 || i == 10 || i == 13 || i == 15 )
                        {
                            brick.Set_HP(3);
                            brick.Set_Color();
                        }
                    }
                    // Create Ball
                    for (int i = 0; i < 1; ++i)
                    {
                        Ball newBall = new Ball(ScreenWidth >> 1, // X위치 스크린 중간
                                (int) objListArr[OBJ_ENUM_BRICK].get(colCount * rowCount - 1).Get_Pos().y + brickWidth + 5, // y위치 최소한 제일 밑에 있는 벽돌 아래
                                BrickWidth >> 1);
                        objListArr[OBJ_ENUM_BALL].add(newBall);

                        // 우상단 45도 방향으로
                        Vector2 newVelocity = new Vector2(1.f, 5.f);
                        newVelocity.Normalize();
                        newVelocity = newVelocity.Multiple(90);
                        newBall.Set_Velocity(newVelocity);
                    }
                    break;
                }

                default: // 예외처리
                    JOptionPane.showMessageDialog(MainFrame.Get_Instance(),
                            "레벨 오류");
                    break;

            }
            GameManager.Get_Instance().TriggerTimer();
        }
    }

    public void Change_BallSpeed(boolean slower)
    {
        float multiple = slower ? 0.5f : 1.5f;
        for (var iter : objListArr[OBJ_ENUM_BALL])
        {
            iter.Set_Velocity(iter.Get_Velocity().Multiple(multiple));
        }
    }
}
