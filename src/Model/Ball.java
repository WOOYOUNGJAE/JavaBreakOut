package Model;

import Utils.Vector2;

public class Ball extends GameObject {
    public Ball(int x, int y, int r, Box box)
    {
        vPos = new Vector2(x, y);
        vVelocity = new Vector2(0, 50);
        radius = r;
        container = box;
    }


    private int radius;
    private Box container = null;

    public int Get_Radius() {return radius;}

    @Override
    public void Update(float deltaTime) {
        Move(deltaTime);
    }

    public void Move(float deltaTime)
    {
        vPos = vPos.Plus(vVelocity.Multiple(deltaTime));
    }

}