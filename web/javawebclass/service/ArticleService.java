package javawebclass.service;

import javawebclass.Bean.Article;
import javawebclass.dbProcess.ArticleDB;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class ArticleService {

    public void show(HttpServletRequest request, HttpServletResponse response){
        ArticleDB service=new ArticleDB();
        List<Article> articles=service.getOpenArticles();
        String title,body,author,date;
        for (Article article:articles){

            title="<h1>"+toHtml(article.getTitle())+"</h1>";
            body="<p>"+article.getArticle()+"</p>";

        }

    }

    private String toPara(String body){
        return body.replaceAll("\\\n","<br>");
    }

    private String toHtml(String text){
        return StringEscapeUtils.escapeHtml4(text);
    }

}
