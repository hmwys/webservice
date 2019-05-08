package classes.opration;

import classes.service.ArticleService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminShow extends HttpServlet {
    ArticleService articleService=new ArticleService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        articleService.adShow(req, resp);
    }
}
