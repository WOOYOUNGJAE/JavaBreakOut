package Model;

public class User {
    public User(String _name)
    {
        name = _name;
        score = 0;
    }

    private String name;
    private int score;

    public String Get_Name()
    {
        return name;
    }
    public int Get_Scroe(){return score;}

    public void Set_Score(int score) {this.score = score;}
}
