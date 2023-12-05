package View;

import Model.GameObject;
import MyGUI.Frame.MainFrame;
import MyGUI.Panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Utils.StaticVariables.*;

public class GameSceneView extends SceneViewBase {
    public GameSceneView(ArrayList<GameObject>[] objListArr) {
        this.objListArr = objListArr;
//        frame = MainFrame.Get_Instance();
    }

    private JPanel topPanel = null; // username,Time,
    private JPanel middlePanel = null; // InGame
    private ArrayList<GameObject>[] objListArr = null;
    private GameObjectWriter[] objWriterArr = new GameObjectWriter[OBJ_ENUM_END];
    private GamePanel gamePanel = new GamePanel();

    @Override
    public void Init_Scene()
    {
        super.Init_Scene();
        frame = MainFrame.Get_Instance();
        container = frame.getContentPane();

        for (int i = 0; i < OBJ_ENUM_END; ++i)
        {
            switch (i)
            {
                case OBJ_ENUM_PLAYER:
                    objWriterArr[i] = new PlayerWriter();
                    break;
                case OBJ_ENUM_BALL :
                    objWriterArr[i] = new BallWriter();
                    break;
                case OBJ_ENUM_WALL:
                    objWriterArr[i] = new WallWriter();
                    break;
            }
        }
        middlePanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                for (int i = OBJ_ENUM_PLAYER; i < OBJ_ENUM_END; ++i)
                {
                    for (var iter : objListArr[i])
                    {
                        if (iter != null)
                        {
                            objWriterArr[i].Draw(iter, g);
                        }
                    }
                }
            }
        };
        frame.getContentPane().add(middlePanel);


        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void Render() {
        frame.repaint();
    }
}
