package View;

import Model.Ball;
import Model.GameObject;

import java.awt.*;

public class GameObjectWriter {
    public void Paint(GameObject gameObject, Graphics g){}
}

class BallWriter extends GameObjectWriter
{
    @Override
    public void Paint(GameObject ball, Graphics g) {
        g.setColor(ball.Get_Color());
        int radius = ((Ball)ball).Get_Radius();
        g.fillOval((int)ball.Get_Pos().x - radius, (int)ball.Get_Pos().y - radius,
                radius * 2, radius *2);
    }
}