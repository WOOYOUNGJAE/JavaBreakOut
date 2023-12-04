package View;

import Model.*;

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
class PlayerWriter extends GameObjectWriter
{
    @Override
    public void Draw(GameObject player, Graphics g) {
        g.setColor(player.Get_Color());
        int width = ((Player)player).Get_Width();
        int height = ((Player)player).Get_Height();
        g.fillRect((int)player.Get_Pos().x - width>>1, (int)player.Get_Pos().y - height>>1, width, height);
    }
}