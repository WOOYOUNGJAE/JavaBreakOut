package View;

import Model.GameObject;
import MyGUI.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Utils.StaticVariables.*;

public class GameSceneView extends SceneViewBase {
    public GameSceneView(ArrayList<GameObject>[] objListArr) {
        frame = MainFrame.Get_Instance();
        container = frame.getContentPane();
        this.objListArr = objListArr;


    }

    private JPanel topPanel = null; // username,Time,
    private JPanel middlePanel = null; // InGame
    private ArrayList<GameObject>[] objListArr = null;
    private GameObjectWriter objWriter = new GameObjectWriter();

    @Override
    public void Init_Scene() {
        super.Init_Scene();
    }

    @Override
    public void Render(Graphics g) {
        for (var iter : objListArr) {
            for (var innerIter : iter) {
                if (innerIter != null) {
                    objWriter.Paint(innerIter, g);
                }
            }
        }
    }
}
