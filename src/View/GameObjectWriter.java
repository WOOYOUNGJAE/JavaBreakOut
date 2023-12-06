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
        int width = (player).Get_Width();
        int height = (player).Get_Height();
        g.fillRect((int)player.Get_Pos().x - (width / 2), (int)player.Get_Pos().y - (height/2 ), width, height);

        // Edge
        g.setColor(Color.CYAN);
        g.fillRect((int)player.Get_Pos().x - (width / 2), (int)player.Get_Pos().y - (height/2 ), width / 10, height); // left
        g.fillRect((int)player.Get_Pos().x + (width / 2) - width / 10, (int)player.Get_Pos().y - (height/2 ), width / 10, height); // right
    }
}
class WallWriter extends GameObjectWriter
{
    @Override
    public void Draw(GameObject wall, Graphics g) {
        g.setColor(wall.Get_Color());
        int width = (wall).Get_Width();
        int height = (wall).Get_Height();
        g.fillRect((int)wall.Get_Pos().x - (width / 2), (int)wall.Get_Pos().y - (height/2 ), width, height);
    }
}
class BrickWriter extends GameObjectWriter
{
    @Override
    public void Draw(GameObject brick, Graphics g) {
        // 죽었으면 그리지 않는다
        if(((Brick)brick).Is_Alive() == false)
        {
            return;
        }
        g.setColor(brick.Get_Color());
        int width = (brick).Get_Width();
        int height = (brick).Get_Height();
        // hp 1일 때는 채워지지 않은 사각형
        if (((Brick)brick).Get_HP() == 1)
        {
            g.drawRect((int)brick.Get_Pos().x - (width / 2), (int)brick.Get_Pos().y - (height/2 ), width, height);
        }
        else
        {
            g.fillRect((int)brick.Get_Pos().x - (width / 2), (int)brick.Get_Pos().y - (height/2 ), width, height);
        }
    }
}