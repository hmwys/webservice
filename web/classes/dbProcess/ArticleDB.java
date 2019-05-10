package classes.dbProcess;

import classes.Bean.Article;
import classes.Dao.ArticleDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArticleDB {

    public Article article(Integer AID, Integer userID, int open) {
        return new Article(AID, userID, open);
    }

    public Article article(Integer AID, String article) {
        return new Article(AID, article);
    }

    private SqlSessionFactory sqlSessionFactory() {
        String resource = "classes/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("配置文件不存在");
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<Article> getOpenArticles() {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        ArticleDao articalDao = sqlSession.getMapper(ArticleDao.class);
        List<Article> articles = articalDao.showOpenArticles();
        sqlSession.close();
        return articles;
    }

    public List<Article> getUserArticles(Integer userID) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        ArticleDao articalDao = sqlSession.getMapper(ArticleDao.class);
        List<Article> articles = articalDao.showUserArticles(userID);
        sqlSession.close();
        return articles;
    }

    public boolean setOpenStatus(Integer AID, Integer userID, int open) {
        SqlSession sqlSession = sqlSessionFactory().openSession(true);
        ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
        boolean result = articleDao.modifyOpenStatus(article(AID, userID, open));
        sqlSession.close();
        return result;
    }


}
