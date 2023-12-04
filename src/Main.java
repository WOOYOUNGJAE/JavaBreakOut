
import Controller.CoreController;

public class Main {
    public static void main(String[] args) {
        CoreController coreController = CoreController.Get_Instance();
        coreController.Initialize();
        coreController.Run();
    }
}