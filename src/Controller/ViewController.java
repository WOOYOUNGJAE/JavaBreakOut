package Controller;

import Model.GameObject;
import View.GameSceneView;
import View.SceneViewBase;
import View.StartSceneView;

import javax.swing.*;
import java.util.ArrayList;

import static Utils.StaticVariables.*;

public class ViewController {

    public ViewController()
    {
        sceneViewArr = new SceneViewBase[SCENE_END];
        sceneViewArr[START_SCENE] = new StartSceneView();
        ArrayList<GameObject>[] objListArr = CoreController.Get_Instance().Get_ObjListArr();
        sceneViewArr[GAME_SCENE] = new GameSceneView(objListArr);
//        sceneViewArr[SCORE_SCENE] = new ScoreSceneView();

        curSceneView = sceneViewArr[START_SCENE];

    }
    private SceneViewBase[] sceneViewArr;
    private SceneViewBase curSceneView = null; // 현재 컨트롤 중인 뷰
    private SceneViewBase prevSceneView = null; // 현재 컨트롤 중인 뷰
    void Initialize()
    {
//        curSceneView.Init_Scene();
    }


    public void Render()
    {
        curSceneView.Render();
    }

    public void Change_SceneView(int nextSceneView)
    {
        prevSceneView = curSceneView;
        curSceneView = sceneViewArr[nextSceneView];

        if (prevSceneView != null)
        {
            prevSceneView.End_Scene();
        }

        curSceneView.Init_Scene();
    }

    public void Add_Label_OnSceneView(JLabel label, int sceneViewIndex)
    {
        sceneViewArr[sceneViewIndex].Add_Label(label);
    }


}
