package Model;

import java.util.ArrayList;

public class UserManager {
    private static UserManager Instance = null;
    public static UserManager Get_Instance()
    {
        if (Instance == null)
        {
            Instance = new UserManager();
        }
        return Instance;
    }

    private ArrayList<User> userList = new ArrayList<>();
    public User curUser = null;
    public void Set_User(User user) {curUser = user;}
    public void Set_User(String strName) {curUser = Find_User(strName);}

    //getter
    public ArrayList<User> Get_UserList() {return userList;}
    // 찾으면 유저 리턴, 리스트에 없으면 null 리턴
    public User Find_User(String userName)
    {
        for (var iter : userList)
        {
            // 이미 있는 이름이라면
            if (iter.Get_Name().equals(userName))
            {
                return iter;
            }
        }
        return null;
    }

    // 성공 true, 실패 false
    public boolean Push_User(String userName)
    {
        if (userName.isEmpty())
        {
            return false;
        }
        // 이미 존재하지 않음
        if (Find_User(userName) == null)
        {
            User newUser = new User(userName);
            userList.add(newUser);
            return true;
        }

        return false;
    }

}
