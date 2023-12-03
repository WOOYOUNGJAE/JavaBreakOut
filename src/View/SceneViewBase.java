package View;

import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.awt.*;

public abstract class SceneViewBase {
    protected boolean isActive = false;
    protected JFrame frame = null;
    protected Container container = null;


    public void Set_Active(boolean b) {isActive = b;}
    public void Init_Scene()
    {

    }

    public void Render(Graphics g)
    {

    }

    public void End_Scene()
    {

    }

}
