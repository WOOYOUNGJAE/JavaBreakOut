package View;

import Model.GameObject;

import java.awt.*;

public class GameObjectWriter {
    public void Draw(GameObject gameObject, Graphics g){}
}

class BallWriter extends GameObjectWriter
{
    @Override
    public void Draw(GameObject ball, Graphics g) {
        g.setColor(ball.Get_Color());
        int radius = ((Ball)ball).Get_Radius();
        g.fillOval((int)ball.Get_Pos().x - radius, (int)ball.Get_Pos().y - radius,
                radius * 2, radius *2);

    }
}