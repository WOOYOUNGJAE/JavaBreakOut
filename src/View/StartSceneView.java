package View;

import Controller.CoreController;
import Model.StartChecker;
import Model.UserManager;
import MyGUI.Frame.MainFrame;
import MyGUI.Panel.PanelWithTextInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Utils.StaticVariables.*;

public class StartSceneView extends SceneViewBase {
    public StartSceneView()
    {
        frame = MainFrame.Get_Instance();
        container = frame.getContentPane();
        startChecker = new StartChecker();
        CoreController.Get_Instance().Apply_StartChecker(startChecker);
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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoreController.Get_Instance().Change_NextScene(GAME_SCENE); // 게임 씬으로 전환
            }
        });
        topPanel.add(label);
        topPanel.add(startButton);
        container.add(topPanel, BorderLayout.NORTH);

        // Middle
        JButton buttonEasy = new JButton("Easy");
        buttonEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChecker.Set_Level(LEVEL_EASY);
                if (startChecker.Is_ReadyToStart())
                {
                    startButton.setEnabled(true);
                }
            }
        });
        middlePanel.add(buttonEasy);
        JButton buttonNormal = new JButton("Normal");
        buttonNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChecker.Set_Level(LEVEL_NORMAL);
                if (startChecker.Is_ReadyToStart())
                {
                    startButton.setEnabled(true);
                }
            }
        });
        middlePanel.add(buttonNormal);
        JButton buttonHard = new JButton("Hard");
        buttonHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChecker.Set_Level(LEVEL_HARD);
                if (startChecker.Is_ReadyToStart())
                {
                    startButton.setEnabled(true);
                }
            }
        });
        middlePanel.add(buttonHard);
        container.add(middlePanel, BorderLayout.CENTER);

        // Bottom, 닉네임 입력
        JButton jButton = new JButton("Enter");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputName = bottomPanel.Get_strTextField();
                // 아무것도 입력되지 않았을 때
                if (inputName.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Please Enter Name.");
                    return;
                }

                // 등록할 수 있는 이름이면 유저 등록하고, 현재 유저로 등록
                if (UserManager.Get_Instance().Push_User(inputName) == true)
                {
                    UserManager.Get_Instance().Set_User(bottomPanel.Get_strTextField());
                    JOptionPane.showMessageDialog(frame, "User Registered.");
                    startChecker.Set_UserSelected(true);
                    jButton.setEnabled(false);
                    if (startChecker.Is_ReadyToStart())
                    {
                        startButton.setEnabled(true);
                    }
                }
                else // 이미 있는 이름
                {
                    JOptionPane.showMessageDialog(frame, "User Already Exists.");
                    startChecker.Set_UserSelected(false);
                }
            }
        });
        bottomPanel.Add_Button(jButton);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel topPanel = null;
    private JPanel middlePanel = null;
    private PanelWithTextInput bottomPanel = null;
    private JLabel label = null;
    private JButton startButton = null;
    private StartChecker startChecker = null; // Model
    @Override
    public void Init_Scene() {

    }

    @Override
    public void End_Scene() {
        container.removeAll();
        frame.revalidate();
        frame.repaint();
        //frame.setVisible(false);
//        container = null;
//        frame = null;
//        topPanel = null;
//        middlePanel = null;
//        bottomPanel = null;
//        label = null;
//        startButton = null;
//        startChecker = null;
    }
}
