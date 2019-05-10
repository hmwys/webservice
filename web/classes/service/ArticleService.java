package classes.service;

import classes.Bean.Article;
import classes.Bean.User;
import classes.dbProcess.ArticleDB;
import classes.dbProcess.UserDB;
import classes.utils.HtmlForm;
import classes.utils.ServiceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleService {
    private ArticleDB service = new ArticleDB();
    private UserDB userDB = new UserDB();
    private Varify varify = new Varify();
    private HtmlForm htmlForm = new HtmlForm();

    /**
     * 默认显示开放文章
     *
     * @param response
     * @throws IOException
     */
    public void show(HttpServletResponse response) throws IOException {
        List<Article> articles = service.getOpenArticles();
        textResponse(response, articles);
    }

    /**
     * 显示管理员文章
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void adShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Article> articles;
        User user = varify.varify(request, response, true);
        if (user.getID() != -1) {
            articles = service.getUserArticles(user.getID());
            textResponse(response, articles, true);
        }
    }

    /**
     * 默认返回给客户端开放文章方法
     *
     * @param response
     * @param articles 文章对象
     * @throws IOException
     */
    private void textResponse(HttpServletResponse response, List<Article> articles) throws IOException {
        textResponse(response, articles, false);
    }

    /**
     * 返回文章数据主方法
     *
     * @param response
     * @param articles
     * @param admin    管理员按钮
     * @throws IOException
     */
    private void textResponse(HttpServletResponse response, List<Article> articles, boolean admin) throws IOException {
        response.setHeader("Content-Type", "text/html");
        response.setCharacterEncoding("utf8");
        if (articles.isEmpty()) {
            response.getWriter().print(htmlForm.articlesAreEmpty());
        } else for (Article article : articles) {
            String articleText;
            articleText = toArticleText(article, admin);
            response.getWriter().print(articleText);
            System.out.println(articleText);
        }
    }

    private String toArticleText(Article article, boolean admin) {
        String title, body, author, date, articleText;
        title = "<h1>" + article.getTitle() + "</h1>";
        body = "<p>" + htmlForm.toPara(article.getArticle()) + "</p>";
        author = "<div class=\"author\">" + userDB.getUserNameByID(article.getUserID()) + "</div>";
        date = "<div class=\"date\">" + new SimpleDateFormat("yyyy-MM-dd").format(article.getDate()) + "</div>";
        articleText = "<div class=\"article\">" + title + htmlForm.toPara(body) + author + date;
        if (admin) {
            articleText += htmlForm.oprationHtml(article);
        }
        articleText += "</div>";
        return articleText;
    }

    /**
     * 设置文章状态，开放或隐藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void setStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceUtils serviceUtils = new ServiceUtils();
        User user = varify.varify(request, response, false, false, false);
        Integer AID = Integer.valueOf(request.getParameter("AID"));
        System.out.println("请求修改的文章编号" + AID);
        int status = Integer.valueOf(request.getParameter("openStatus"));
        System.out.println("当前的文章状态" + status);
        status = serviceUtils.modifyArticleOpenStatus(status);
        System.out.println("请求修改的状态" + status);
        boolean result = service.setOpenStatus(AID, user.getID(), status);
        System.out.println("更改状态" + result);
        response.sendRedirect("admin.html");
    }
}
