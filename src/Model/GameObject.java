package Model;

import Utils.Vector2;

import java.awt.*;

// 게임오브젝트 부모 모델
public abstract class GameObject {

    protected int objectEnum = -1;
    public int Get_ObjEnum() {return objectEnum;}
    protected Vector2 vPos;
    protected Vector2 vVelocity;
    protected boolean toBeDeleted = false;
    public boolean isToBeDeleted() {return toBeDeleted;}
    protected int width;
    protected int height;

    public Vector2 Get_Pos() {return vPos;}
    public Vector2 Get_Velocity() {return vVelocity;}

    // getter
    // Draw Property
    protected Color color = Color.black;


    protected int radius;

    public int Get_Radius() {return radius;}
    // Getter Setter
    public int Get_Width() {return width;}
    public int Get_Height() {return height;}
    public Color Get_Color() {return color;}
    public void Set_Color(Color c) {this.color = c;}
    public void Update(float deltaTime)
    {
        
    }
    public void Move(float deltaTime){}

    public void OnCollision(int collidedDir, float collidedLength, GameObject collidedObject) {}
}