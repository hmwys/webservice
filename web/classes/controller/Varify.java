package classes.controller;


import classes.Bean.User;
import classes.Bean.UserCookie;
import classes.Bean.UserSession;
import classes.dbProcess.UserDB;
import classes.utils.CollectionUtils;

import javax.servlet.http.*;
import java.io.IOException;

public class Varify {
    private UserDB service = new UserDB();
    private CollectionUtils collectionUtils=new CollectionUtils();

    public void varify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        varify(request, response, true, false,true);
    }

    public User varify(HttpServletRequest request, HttpServletResponse response, boolean admin) throws IOException {
        return varify(request, response, false, true,true);
    }

    public void varify(HttpServletRequest request, HttpServletResponse response,boolean login,boolean admin) throws IOException {
        varify(request, response, false, false,true);
    }

    /**
     * @param request
     * @param response
     * @param login    区分登录和验证操作
     * @param admin   区分登陆页面和管理界面
     * @param requireResp 有些请求不需要response
     * @throws IOException
     */
    public User varify(HttpServletRequest request, HttpServletResponse response, boolean login, boolean admin,boolean requireResp) throws IOException {

        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Type", "text/html");

        HttpSession session = request.getSession();
        UserSession userSession = checkSession(session);
        Cookie[] cookie = request.getCookies();
        UserCookie userCookie = null;

        if (cookie != null) {
            userCookie = service.userCookie(cookie);
            userCookie = checkUserCookie(userCookie);
        }
        User user = user();
        if (userSession.getUserID() == -1) {    //使用session判断当前是否已经有用户登录
            if (userCookie == null) {           //coookie判断当前是否已经有用户登录
                if (login) {
                    user = login(request, response, userSession, userCookie);
                } else if (admin) {
                    response.sendRedirect("loginPage.html");
                } else {
                    response.getWriter().print(-1);
                }
                System.out.println("session and cookie don\'t work and cookie=null");
            } else if (userCookie.getUserID() == -1) {
                System.out.println("-------------------session and cookie don\'t work and cookie\'s userID=" + userCookie.getUserID() + "---------------------");

                if (login) {
                    user = login(request, response, userSession, userCookie);//不能与上条合并,可能会空指针
                } else if (admin) {
                    response.sendRedirect("loginPage.html");
                } else {
                    response.getWriter().print(-1);

                }
            } else if (requireResp){
                System.out.println("---------------session  doesn\'t work but cookie work--------------------");

                user = loginByCookie(userSession, userCookie);
                response.setStatus(200);
                if (login) {
//                    response.getWriter().println("The user '" + user.getUserName() + "' has logged");
                    response.getWriter().println(user.getUserName() + "已经登陆，请刷新");
                } else if (admin) {
                    response.getWriter().print("<h1>欢迎回来," + user.getUserName() + "</h1>");
                } else {
                    response.getWriter().print(user.getID());
                }
            }
        } else {
            System.out.println("---------------session   works --------------------");

            user = loginBySession(userSession, userCookie);
            response.setStatus(200);
            if (login) {
//                response.getWriter().println("The user '" + user.getUserName() + "' has logged");
                response.getWriter().println(user.getUserName() + "已经登陆，请刷新");

            } else if (admin) {
                response.getWriter().print("<h1>欢迎回来," + user.getUserName() + "</h1>");
            } else if (requireResp){
                response.getWriter().print(user.getID());
            }
        }


        System.out.println(user);
        return user;

    }

    private User login(HttpServletRequest request, HttpServletResponse response, UserSession userSession, UserCookie userCookie) throws IOException {
        User user = service.checkUser(collectionUtils.toRecognizableMap(request.getParameterMap()));
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
                if (service.checkCookie(userCookie)!=user.getID()) {
                    userCookie.setUserID(user.getID());
                    service.addCookie(userCookie);
                    addCookieResult = true;
                }else System.out.println("cookie冲突");
            } else System.out.println("cookie为null");
            System.out.println("cookie:" + addCookieResult);


            boolean updateSessionResult = false;
            userSession.setUserID(user.getID());
            if (service.updateSession(userSession)) {
                updateSessionResult = true;
            } else addUserSession(userSession);
            System.out.println("updateSession:" + updateSessionResult);
            response.getWriter().print("success");
            return user;
        } else {
            response.getWriter().println("登录失败，帐号或密码错了吧");
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

    private void loginSuccessResponse(HttpServletResponse response, User user) throws IOException {
        response.sendRedirect("admin.html");
        response.getWriter().print("<h1>欢迎回来," + user.getUserName() + "</h1>");

    }
}