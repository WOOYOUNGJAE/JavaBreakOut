package View;

import Controller.CoreController;
import Model.StartChecker;
import Model.User;
import Model.UserManager;
import MyGUI.Frame.MainFrame;
import MyGUI.Panel.PanelWithTextInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static Utils.StaticVariables.*;

public class StartSceneView extends SceneViewBase {
    public StartSceneView()
    {
        Init_Scene();
    }

    private JPanel topPanel = null;
    private JPanel middlePanel = null;
    private PanelWithTextInput bottomPanel = null;
    private JLabel label = null;
    private JButton startButton = null;
    private StartChecker startChecker = null; // Model
    @Override
    public void Init_Scene() {
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

        // 유저 순위 패널
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        Vector<String> listData = new Vector<>();
        var userList = UserManager.Get_Instance().Get_UserList();
        for (var iter : userList)
        {
            listData.add(iter.Get_Name() + " : " + iter.Get_Score() + "\n");
        }
        // 점수에 따라 정렬
        Collections.sort(listData, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(Integer.parseInt(o2.split(":")[1].trim()), Integer.parseInt(o1.split(":")[1].trim()));
            }
        });

        for (int i = 0; i < listData.size(); ++i)
        {
            listData.set(i, (i+1) + ".  " + listData.get(i));
        }



        JList<String> jList = new JList<>(listData);
        JScrollPane scrollPane = new JScrollPane(jList);
        middlePanel.add(scrollPane);


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

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void End_Scene() {
        container.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
