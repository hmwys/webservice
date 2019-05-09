package classes.opration;

import classes.service.Varify;

import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Varify varify = new Varify();
        varify.clean(req, resp);
    }
}
