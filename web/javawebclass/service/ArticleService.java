package javawebclass.service;

import javawebclass.Bean.Article;
import javawebclass.Dao.ArticleDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArticleService {
    private SqlSessionFactory sqlSessionFactory() {
        String resource = "mybatis-config.xml";
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


}
