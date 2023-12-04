package MyGUI.Panel;

import javax.swing.*;
import java.awt.*;

import static Utils.StaticVariables.ScreenHeight;
import static Utils.StaticVariables.ScreenWidth;

public class GamePanel extends JPanel {

    public GamePanel()
    {
        super();
        setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(50, 50,
                50, 50);

        g.fillRect(0, 0, 500, 500);
        g.drawRect(0, 0, 500, 500);
        }
}
