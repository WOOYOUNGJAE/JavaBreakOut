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

    private ArrayList<User> userList;

    // 이미 존재하면 false, 정상적으로 입력되었으면 true
    public boolean Push_User(String userName)
    {
        for (var iter : userList)
        {
            // 이미 있는 이름이라면
            if (iter.Get_Name().equals(userName))
            {
                return false;
            }
        }
        userList.add(new User(userName));

        return true;
    }

}
