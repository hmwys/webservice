package classes.Dao;

import classes.Bean.User;
import classes.Bean.UserCookie;
import classes.Bean.UserSession;

public interface UserDao {
    User getUserInfByID(Integer ID);

    User getUserInfByUsername(String username);

    User getUserInfByEmail(String email);

    User login(User user);


    boolean register(User user);

    UserSession getUserIDBySession(String sid);

    UserCookie getUserIDByCookie(UserCookie userCookie);

    boolean addUserCookie(UserCookie userCookie);

    boolean updateUserCIDByCookie(String name, String value);

    boolean addUserSession(UserSession session);

    boolean updateSessionByUserID(UserSession session);

    boolean delSession(UserSession session);

    boolean delCookie(UserCookie userCookie);

    boolean delUser(User user);

    boolean modifyPasswd(User user);

    User getUserNameByID(Integer integer);

}
