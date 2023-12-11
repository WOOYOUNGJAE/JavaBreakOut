package Model;

import javax.swing.*;

// 시간, 스코어 관리
public class GameManager
{

    public GameManager()
    {
        timeAndScoreLabel = new JLabel();
        timeAndScoreLabel.setText("Elapsed Time : " + accTime + " Current User : " + UserManager.Get_Instance().curUser.Get_Name());
    }
    public static GameManager Instance = null;

    public static GameManager Get_Instance()
    {
        if (Instance == null)
        {
            Instance = new GameManager();
        }
        return Instance;
    }

    public boolean checkTime = false;
    public long startTime = 0;
    public long accTime = 0;
    public JLabel timeAndScoreLabel;

    public void TriggerTimer()
    {
        checkTime = true;
        startTime = System.currentTimeMillis();
    }
    public void CompleteTimer()
    {
        checkTime = false;
        startTime = 0;
    }


    public void Update()
    {
        if (checkTime == true)
        {
            accTime = System.currentTimeMillis() - startTime;
            timeAndScoreLabel.setText("Elapsed Time : " + accTime / 1000 + " Current User : " + UserManager.Get_Instance().curUser.Get_Name());
        }
    }
}
