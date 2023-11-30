package Controller;

import View.GameSceneView;
import View.SceneView;
import View.ScoreSceneView;
import View.StartSceneView;

import static Utils.StaticVariables.*;

public class ViewController {

    public ViewController()
    {
        sceneViewArr[START_SCENE_VIEW] = new StartSceneView();
        sceneViewArr[GAME_SCENE_VIEW] = new GameSceneView();
        sceneViewArr[SCORE_SCENE_VIEW] = new ScoreSceneView();

        curSceneView = sceneViewArr[START_SCENE_VIEW];
    }

    private SceneView[] sceneViewArr = new SceneView[SCENE_VIEW_END];
    private SceneView curSceneView; // 현재 컨트롤 중인 뷰

    void Initialize()
    {
    }

    public void Run()
    {

    }

    public void Change_SceneView(int nextSceneView)
    {
        curSceneView = sceneViewArr[nextSceneView];
        
        // TODO : 교체되면서 할 것 들
    }

}
