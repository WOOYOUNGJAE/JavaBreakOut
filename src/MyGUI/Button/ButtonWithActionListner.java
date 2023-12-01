package MyGUI.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonWithActionListner extends JButton implements ActionListener {
    ButtonWithActionListner(String strInput)
    {
        super(strInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
