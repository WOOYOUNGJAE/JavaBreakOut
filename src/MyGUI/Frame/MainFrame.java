package MyGUI.Frame;
import Controller.KeyboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utils.StaticVariables.*;

// Singleton
public class MainFrame extends JFrame implements ActionListener {

    static private MainFrame Instance = new MainFrame();
    public MainFrame()
    {
        super();
    }
    static public MainFrame Get_Instance()
    {
        if (Instance == null)
        {
            Instance = new MainFrame();
        }

        return Instance;
    }
    public void actionPerformed(ActionEvent e)
    {
        
    }

    public void Initialize()
    {
        setSize(ScreenWidth, ScreenHeight);
        Set_Active(true);
    }

    public void Set_Active(boolean b)
    {
        setVisible(b);
    }

    public void Clear()
    {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }
}
