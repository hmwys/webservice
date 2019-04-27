package javawebclass.opration;

import javawebclass.Bean.UserCookie;
import javawebclass.Bean.UserSession;
import javawebclass.service.UserService;

import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet {

    UserService service = new UserService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie[] = req.getCookies();
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
        resp.getWriter().println("Logout");
    }
}
