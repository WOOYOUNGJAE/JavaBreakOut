package View;

import Model.GameManager;
import Model.GameObject;
import MyGUI.Frame.MainFrame;

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

    @Override
    public void Init_Scene()
    {
        super.Init_Scene();
        frame = MainFrame.Get_Instance();
        container = frame.getContentPane();
        frame.setBackground(Color.WHITE);
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
                case OBJ_ENUM_BRICK:
                    objWriterArr[i] = new BrickWriter();
                    break;
                case OBJ_ENUM_ITEM:
                    objWriterArr[i] = new ItemWriter();
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

        topPanel = new JPanel();
        topPanel.add(GameManager.Get_Instance().timeAndScoreLabel);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void Render() {
        if (frame == null) // 예외처리 : frame이 준비되기 전에 Render이 실행되는 상황 방지
        {
            return;
        }
        frame.repaint();
    }


    @Override
    public void End_Scene() {
        container.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
