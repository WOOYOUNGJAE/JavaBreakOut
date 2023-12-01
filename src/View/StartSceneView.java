package View;

import Model.UserManager;
import MyGUI.Button.ButtonWithActionListner;
import MyGUI.Frame.MainFrame;
import MyGUI.Panel.PanelWithTextInput;

import javax.swing.*;
import java.awt.*;

public class StartSceneView  extends SceneView{
    public StartSceneView()
    {
        frame = MainFrame.Get_Instance();
        container = frame.getContentPane();
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new PanelWithTextInput(
                "Enter Nickname : ",
                20,
                1);

        container.setLayout(new BorderLayout());
        label = new JLabel("Press This: ");

        // Top
        startButton = new JButton("Game Start");
        startButton.setEnabled(false);
        topPanel.add(label);
        topPanel.add(startButton);
        container.add(topPanel, BorderLayout.NORTH);

        // Bottom, 닉네임 입력
        JButton jButton = new JButton("Enter");
//        jButton.addActionListener(UserManager.Get_Instance().Push_User());
        bottomPanel.Add_Button(jButton);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }
    private JFrame frame = null;
    private Container container = null;
    private JPanel topPanel = null;
    private JPanel middlePanel = null;
    private PanelWithTextInput bottomPanel = null;
    private JLabel label = null;
    private JButton startButton = null;
    @Override
    public void Init_Scene() {

    }

    @Override
    public void End_Scene() {
//        container.removeAll();
        container = null;
        frame = null;
    }
}
