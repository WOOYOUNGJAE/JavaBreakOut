package Controller;

import Model.GameObject;
import Model.StartChecker;
import MyGUI.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Utils.StaticVariables.*;

// 가장 상위 컨트롤러(매니저), 하위 클래스에서 손쉽게 접근할 수 있도록 싱글톤으로
public class CoreController {
    public CoreController()
    {

    }
    private static CoreController Instance = new CoreController();
    public static CoreController Get_Instance()
    {
        if (Instance == null)
        {
            Instance = new CoreController();
        }
        return Instance;
    }
    private ArrayList<GameObject>[] objListArr = new ArrayList[OBJ_ENUM_END];;
    private int curScene = START_SCENE;
    private StartChecker startChecker = null;
    public void Apply_StartChecker(StartChecker instance) {startChecker = instance;}
    public StartChecker Get_StartChecker() {return startChecker;}
    MainFrame mainFrame = null;
    ModelController modelController = null;
    ViewController viewController = null;
    public ArrayList<GameObject>[] Get_ObjListArr() {return objListArr; }
    public void Add_GameObject(GameObject obj, int objEnum)
    {
        objListArr[objEnum].add(obj);
    }
    public void Initialize()
    {
        for (int i = 0; i < objListArr.length; ++i)
        {
            objListArr[i] = new ArrayList<>();
        }
        mainFrame = MainFrame.Get_Instance();
        modelController = new ModelController();
        modelController.Initialize();
        viewController = new ViewController();
        viewController.Initialize();
        mainFrame.Initialize();
        viewController.Initialize();
    }
    // Main에서 호출
    public static float deltaTime = 0.1f;
    public void Run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e){}

            modelController.Update(deltaTime);
            viewController.Render();
        }
    }

    public void Change_NextScene(int nextScene)
    {
        curScene = nextScene;
        modelController.Change_SceneView(nextScene);
        viewController.Change_SceneView(nextScene);
    }

    public void Remove_Object(GameObject obj, int objEnum)
    {
        if (objListArr[objEnum].remove(obj) == false)
        {
            // 예외처리
            JOptionPane.showMessageDialog(MainFrame.Get_Instance(),
                    "오브젝트 삭제 실패");
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


    public JLabel PopUp_Result(boolean isCleared)
    {
        String str = isCleared ? "Clear" : "Game Over";
        JLabel jLabel = new JLabel(str);


        return jLabel;
    }

    public void Add_Label_OnSceneView(JLabel label, int sceneViewIndex)
    {
        viewController.Add_Label_OnSceneView(label, sceneViewIndex);
    }

}
