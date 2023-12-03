package Utils;

public class Vector2 {
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2() {}

    public float x = 0;
    public float y = 0;

    public Vector2 Plus(Vector2 rhs)
    {
        Vector2 vec = new Vector2(x + rhs.x, y + rhs.y);
        return vec;
    }
    public Vector2 Multiple(Vector2 rhs)
    {
        Vector2 vec = new Vector2(x * rhs.x, y * rhs.y);
        return vec;
    }
    public Vector2 Multiple(float f)
    {
        Vector2 vec = new Vector2(x * f, y * f);
        return vec;
    }

    public Vector2 Normalize()
    {
        float length = (float)Math.sqrt(x*x + y*y);
        x /= length; y/= length;
        return this;
    }

}
