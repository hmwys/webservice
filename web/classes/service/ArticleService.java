package classes.service;

import classes.Bean.Article;
import classes.dbProcess.ArticleDB;
import classes.dbProcess.UserDB;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleService {

    public void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArticleDB service=new ArticleDB();
        UserDB userDB=new UserDB();
        List<Article> articles=service.getOpenArticles();
        String title,body,author,date,articleText;
        for (Article article:articles){

            title="<h1>"+toHtml(article.getTitle())+"</h1>";
            body="<p>"+toHtml(article.getArticle())+"</p>";
            author="<div class=\"author\">"+userDB.getUserNameByID(article.getUserID())+"</div>";
            date="<div class=\"date\">"+ new SimpleDateFormat("yyyy-MM-dd").format(article.getDate()) +"</div>";
            articleText="<div class=\"article\">"+title+toPara(body)+author+date+"</div>";
            response.setHeader("Content-Type","text/html");
            response.setCharacterEncoding("utf8");
            response.getWriter().print(articleText);
            System.out.println(articleText);
        }

    }

    private String toPara(String body){
        return body.replaceAll("\\\n","<br>");
    }

    private String toHtml(String text){
        return StringEscapeUtils.escapeHtml4(text);
    }

}
