package classes.Dao;

import classes.Bean.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> showOpenArticles();

    List<Article> showUserArticles(Integer userID);

    boolean modifyOpenStatus(Article article);

}
