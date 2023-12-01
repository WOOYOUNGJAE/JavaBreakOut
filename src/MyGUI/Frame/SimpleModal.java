package MyGUI.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utils.StaticVariables.ScreenHeight;
import static Utils.StaticVariables.ScreenWidth;

// OK 버튼 하나 있는 알림 다이어로그(모달)
public class SimpleModal extends JFrame {
    public SimpleModal(String str)
    {
        setSize(300, 150);
        setVisible(true);
        button = new JButton(str);
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JOptionPane.showMessageDialog(this, "Error");
    }
    private JButton button;
}
