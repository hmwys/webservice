package classes.opration;

import classes.service.Varify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckSession extends HttpServlet {
    Varify varify = new Varify();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        varify.varify(req, resp, false,false);
    }
}
