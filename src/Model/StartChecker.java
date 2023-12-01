package Model;

import static Utils.StaticVariables.*;

// 사용중인 유저가 있는지, 레벨을 선택했는지 판단해서 시작 가능 여부를 판단해주는 Model
public class StartChecker {
    private boolean userSelected = false;
    private int level = 0; // Easy1 Normal2 Hard3

    // getter
    public boolean Is_ReadyToStart() {return userSelected && Is_LevelSelected();}
    public boolean Is_LevelSelected() {return (LEVEL_EASY <= level) && (level <= LEVEL_HARD);}
    // setter
    public void Set_UserSelected(boolean b) {userSelected = b;}
    public void Set_Level(int i) {level = i;}
}
