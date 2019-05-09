import classes.Bean.Article;
import classes.dbProcess.ArticleDB;
import classes.dbProcess.UserDB;
import org.apache.commons.text.StringEscapeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class articleTest {
    public static void main(String[] args) {
        ArticleDB service = new ArticleDB();
        UserDB userDB = new UserDB();
        List<Article> articles = service.getOpenArticles();
        String title, body, author, date, articleText;
        for (Article article : articles) {

            title = "<h1>" + toHtml(article.getTitle()) + "</h1>";
            body = "<p>" + toHtml(article.getArticle()) + "</p>";
            author = "<div class=\"author\">" + userDB.getUserNameByID(article.getUserID()) + "</div>";
            date = "<div class=\"date\">" + new SimpleDateFormat("yyyy-MM-dd").format(article.getDate()) + "<>";
            articleText = title + toPara(body) + author + date;
            System.out.println(articleText);
        }

    }

    private static String toPara(String body) {
        return body.replaceAll("\\\n", "<br>");
    }

    private static String toHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }
}
