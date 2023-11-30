package MyGUI.Frame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utils.StaticVariables.*;

public class MainFrame extends JFrame implements ActionListener {

    public void actionPerformed(ActionEvent e)
    {
        
    }

    public void Initialize()
    {
        setSize(ScreenWidth, ScreenHeight);

    }

    public void Set_Active(boolean b)
    {
        setVisible(b);
    }

}
