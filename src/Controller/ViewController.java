package Controller;

import View.GameSceneView;
import View.SceneView;
import View.ScoreSceneView;
import View.StartSceneView;

import static Utils.StaticVariables.*;

public class ViewController {

    public ViewController()
    {
        sceneViewArr = new SceneView[SCENE_VIEW_END];
        sceneViewArr[START_SCENE_VIEW] = new StartSceneView();
        sceneViewArr[GAME_SCENE_VIEW] = new GameSceneView();
        sceneViewArr[SCORE_SCENE_VIEW] = new ScoreSceneView();

        curSceneView = sceneViewArr[START_SCENE_VIEW];
    }

    private SceneView[] sceneViewArr;
    private SceneView curSceneView = null; // 현재 컨트롤 중인 뷰
    private SceneView prevSceneView = null; // 현재 컨트롤 중인 뷰

    void Initialize()
    {
        curSceneView.Init_Scene();
    }


    public void Render()
    {
        curSceneView.Render();
    }

    public void Change_SceneView(int nextSceneView)
    {
        prevSceneView = curSceneView;
        curSceneView = sceneViewArr[nextSceneView];
        
        // TODO : 교체되면서 할 것 들
        if (prevSceneView != null)
        {
            prevSceneView.End_Scene();
        }

        curSceneView.Init_Scene();
    }

}
