package Controller;

import MyGUI.Frame.MainFrame;

// 가장 상위 컨트롤러(매니저), 하위 클래스에서 손쉽게 접근할 수 있도록 싱글톤으로
public class CoreController {

    MainFrame mainFrame = new MainFrame();
    ModelController modelController = new ModelController();
    ViewController viewController = new ViewController();
    public void Initialize()
    {
        mainFrame.Initialize();
    }

    // Main에서 호출
    public void Run()
    {

    }
}
