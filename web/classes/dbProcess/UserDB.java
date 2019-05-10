package classes.dbProcess;

import classes.Bean.User;
import classes.Bean.UserCookie;
import classes.Bean.UserSession;
import classes.Dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class UserDB {

    public User user() {
        return new User(-1);
    }

    public UserCookie userCookie(String cname, String cvalue) {
        return new UserCookie(cname, cvalue);
    }

    public UserCookie userCookie(Cookie[] cookies) {
        return new UserCookie(cookies[0].getName(), cookies[0].getValue());
    }

    public UserSession userSession(String sid, Integer ID) {
        return new UserSession(sid, ID);
    }

    private SqlSessionFactory sqlSessionFactory() {
        String resource = "classes/mybatis-config.xml";
        InputStream inputStream = null;
        SqlSessionFactory sqlSessionFactory = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("配置文件不存在");
            return null;
        }

    }

    public User getUserByID(Integer ID) {

        if (ID == -1) {
            return null;
        } else {
            SqlSession sqlSession = sqlSessionFactory().openSession(true);
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.getUserInfByID(ID);
            sqlSession.close();
            return user;
        }
    }

    public User login(User user) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        user = userDao.login(user);
        sqlSession.close();
        if (user == null) {
            System.out.println("登录失败");
            return null;
        } else {
            System.out.println("登陆成功");
            System.out.println(user.toString());
            return user;
        }
    }

    public User loginByID(Integer ID) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUserInfByID(ID);
        sqlSession.close();
        return user;
    }

    public boolean reg(User user) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        boolean result = userDao.register(user);
        sqlSession.close();
        if (result) {
            System.out.println("注册成功！");
        }
        return result;

    }

    public int checkCookie(UserCookie userCookie) {

        if (userCookie == null) {
            return -1;
        } else {
            SqlSession sqlSession = sqlSessionFactory().openSession(true);
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userCookie = userDao.getUserIDByCookie(userCookie);
            sqlSession.close();
            if (userCookie == null) {
                return -1;
            } else {
                sqlSession.close();
                return userCookie.getUserID();
            }
        }
    }

    public boolean addCookie(UserCookie userCookie) {
        if (userCookie == null) {
            return false;
        } else {
            SqlSession sqlSession = sqlSessionFactory().openSession(true);
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            boolean result = userDao.addUserCookie(userCookie);
            sqlSession.close();
            return result;
        }
    }

    public User checkUser(Map<String, String> map) {
        return new User(map.get("userName"), map.get("password"));
    }


    /**
     * @param sid
     * @return
     */
    public int checkSession(String sid) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        UserSession userSession = userDao.getUserIDBySession(sid);
        sqlSession.close();
        if (userSession == null) {
            return -1;
        } else return userSession.getUserID();

    }

    public boolean addSession(String sid, Integer ID) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        boolean result = userDao.addUserSession(userSession(sid, ID));
        sqlSession.close();
        return result;
    }

    public boolean updateSession(UserSession userSession) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        boolean result = userDao.updateSessionByUserID(userSession);
        sqlSession.close();
        return result;
    }

    public boolean delSession(UserSession userSession) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        boolean result = userDao.delSession(userSession);
        sqlSession.close();
        return result;
    }

    public boolean delCookie(UserCookie userCookie) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        boolean result = userDao.delCookie(userCookie);
        sqlSession.close();
        return result;
    }

    public String getUserNameByID(Integer userID) {

        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUserNameByID(userID);

        sqlSession.close();
        if (user == null) return null;
        else return user.getUserName();
    }

}

