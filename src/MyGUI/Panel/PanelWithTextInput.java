package MyGUI.Panel;

import javax.swing.*;

public class PanelWithTextInput extends JPanel {
    public PanelWithTextInput(String _strLabel, int textColumns , int buttonNumMax)
    {
        strLabel = _strLabel;
        label = new JLabel(strLabel);
        textField = new JTextField(textColumns);
        buttonArr = new JButton[buttonNumMax]; // 넣을 수 있는 최대 버튼 개수
        add(label);
        add(textField);
    }


    private String strLabel;
    private JLabel label;
    private String strTextField;
    private JTextField textField;

    // Button
    JButton[] buttonArr;

    public void Add_Button(JButton jButton)
    {
        for (var iter : buttonArr)
        {
            if (iter == null)
            {
                iter = jButton;
                add(iter);
                return;
            }
        }

        // 이미 꽉 차 있으면

    }
}
