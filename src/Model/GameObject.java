package Model;

import Utils.Vector2;

import java.awt.*;

// 게임오브젝트 부모 모델
public abstract class GameObject {

    protected Vector2 vPos;
    protected Vector2 vVelocity;
    public Vector2 Get_Pos() {return vPos;}
    public Vector2 Get_Velocity() {return vVelocity;}
    // Draw Property
    protected Color color = Color.black;

    // Getter Setter
    public Color Get_Color() {return color;}
    public void Set_Color(Color c) {this.color = c;}
    public void Update(float deltaTime)
    {
        
    }
    public void Move(float deltaTime){}
}