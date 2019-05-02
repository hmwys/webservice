package javawebclass.service;


import javawebclass.Bean.User;
import javawebclass.Bean.UserCookie;
import javawebclass.Bean.UserSession;
import javawebclass.dbProcess.UserDB;

import javax.servlet.http.*;
import java.io.IOException;

public class Varify  {
    private UserDB service = new UserDB();

    public void varify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        varify(request, response, true);
    }

    /**
     * @param request
     * @param response
     * @param login //区分登录和验证操作
     * @throws IOException
     */
    public void varify(HttpServletRequest request, HttpServletResponse response, boolean login) throws IOException {

        HttpSession session = request.getSession();
        UserSession userSession = checkSession(session);
        Cookie[] cookie = request.getCookies();
        UserCookie userCookie = null;

        if (cookie != null) {
            userCookie = service.userCookie(cookie);
            userCookie = checkUserCookie(userCookie);
        }
        User user = user();
        if (userSession.getUserID() == -1) {//使用session判断当前是否已经有用户登录
            if (userCookie == null) {
                if (login) {
                    user = login(request, response, userSession, userCookie);
                } else response.getWriter().print(-1);
                System.out.println("session and cookie don\'t work and cookie=null");
            }//coookie判断当前是否已经有用户登录
            else if (userCookie.getUserID() == -1) {
                System.out.println("-------------------session and cookie don\'t work and cookie\'s userID=" + userCookie.getUserID() + "---------------------");

                if (login) {
                    user = login(request, response, userSession, userCookie);//不能与上条合并,可能会空指针
                } else response.getWriter().print(-1);
            } else {
                System.out.println("---------------session  doesn\'t work but cookie work--------------------");

                user = loginByCookie(userSession, userCookie);
                response.setStatus(200);
                if (login) response.getWriter().println("The user '" + user.getUserName() + "' has logged");
                else response.getWriter().print(user.getID());
            }
        } else {
            System.out.println("---------------session   works --------------------");

            user = loginBySession(userSession, userCookie);
            response.setStatus(200);
            if (login) response.getWriter().println("The user '" + user.getUserName() + "' has logged");
            else response.getWriter().print(user.getID());

        }


        System.out.println(user);

    }

    private User login(HttpServletRequest request, HttpServletResponse response, UserSession userSession, UserCookie userCookie) throws IOException {
        User user = service.checkUser(service.toRecognizableMap(request.getParameterMap()));
        user = service.login(user);
        if (userCookie != null)
            userCookie = service.userCookie(request.getCookies());

        if (user != null) {
            if (!user.getID().equals(userSession.getUserID())) service.delSession(userSession);
            if (userCookie != null) {
                if (!user.getID().equals(checkUserCookie(userCookie).getUserID())) {//不能用&合并判断，可能会空指针
                    service.delCookie(userCookie);
                }
            }

            boolean addCookieResult = false;
            if (userCookie != null) {
                userCookie.setUserID(user.getID());
                service.addCookie(userCookie);
                addCookieResult = true;
            } else System.out.println("cookie为null");
            System.out.println("cookie:" + addCookieResult);


            boolean updateSessionResult = false;
            userSession.setUserID(user.getID());
            if (service.updateSession(userSession)) {
                updateSessionResult = true;
            } else addUserSession(userSession);
            System.out.println("updateSession:" + updateSessionResult);
            response.getWriter().println("<body>Login success</body>");
            response.setStatus(203);
            return user;
        } else {
            response.getWriter().println("<body>Login failed</body>");
            response.setStatus(200);

            return user();
        }

    }

    private User loginByID(Integer ID) {

        return service.loginByID(ID);
    }

    private User loginByCookie(UserSession userSession, UserCookie userCookie) {
        System.out.println("-----------------cookie--------------------");

        System.out.println("Login by cookie and try to update session");
        System.out.println("-----------------cookie--------------------");

        userSession.setUserID(userCookie.getUserID());
        service.updateSession(userSession);
        System.out.println(userSession);
        System.out.println(userCookie);
        return loginByID(userCookie.getUserID());
    }

    private User loginBySession(UserSession userSession, UserCookie userCookie) {
        System.out.println("-----------------session--------------------");
        System.out.println("Login by session and try to add cookie");
        System.out.println(userSession);
        System.out.println(userCookie);
        System.out.println("-----------------session--------------------");

        if (userCookie != null) {
            if (!checkUserCookie(userCookie).getUserID().equals(userSession.getUserID())) {
                userCookie.setUserID(userSession.getUserID());
                service.addCookie(userCookie);
                System.out.println(userSession);
                System.out.println(userCookie);
            } else System.out.println("cookie已存在");
        } else System.out.println("cookie=null ,failed to add it");
        return loginByID(userSession.getUserID());
    }

    private UserCookie checkUserCookie(UserCookie userCookie) {

        userCookie.setUserID(service.checkCookie(userCookie));

        if (userCookie == null) {
            System.out.println("cookie不存在");
            return null;
        } else {
            System.out.println(userCookie.toString());
            return userCookie;
        }
    }

    private UserSession checkSession(HttpSession httpSession) {
        UserSession userSession = service.userSession(httpSession.getId(), null);
        userSession.setUserID(service.checkSession(httpSession.getId()));
        if (userSession.getUserID() == -1) {
            System.out.println("会话不存在或已过期");
        }
        return userSession;

    }

    private boolean addUserSession(UserSession userSession) {
        return service.addSession(userSession.getSid(), userSession.getUserID());
    }

    private User user() {
        return service.user();
    }

    public void clean(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookie = req.getCookies();
        HttpSession httpSession = req.getSession();
        UserCookie userCookie = null;
        if (cookie != null) {
            userCookie = service.userCookie(cookie);
        }
        if (userCookie != null) {
            userCookie.setUserID(service.checkCookie(userCookie));
            service.delCookie(userCookie);
        }


        UserSession userSession = service.userSession(httpSession.getId(), service.checkSession(httpSession.getId()));
        service.delSession(userSession);
        resp.sendRedirect("index.html");
    }
}