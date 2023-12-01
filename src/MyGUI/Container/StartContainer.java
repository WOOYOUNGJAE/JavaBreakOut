package MyGUI.Container;

import javax.swing.*;
import java.awt.*;

public class StartContainer extends Container{

    StartContainer()
    {
        label = new JLabel("Press This: ");
        button = new JButton("OK");
        setLayout(new FlowLayout());
        add(label);
        add(button);
    }
    private JLabel label = null;
    private JButton button = null;
}
