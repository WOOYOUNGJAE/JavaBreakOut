package Controller;

import MyGUI.Frame.MainFrame;
import Utils.StaticVariables;

import javax.swing.*;

// 가장 상위 컨트롤러(매니저), 하위 클래스에서 손쉽게 접근할 수 있도록 싱글톤으로
public class CoreController {

    MainFrame mainFrame = MainFrame.Get_Instance();
    ModelController modelController = new ModelController();
    ViewController viewController = new ViewController();
    public void Initialize()
    {
        mainFrame.Initialize();
        viewController.Initialize();
    }

    // Main에서 호출
    public void Run()
    {
        while (true)
        {
            int a = 1;
        }
    }
}
