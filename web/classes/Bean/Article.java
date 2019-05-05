package classes.Bean;

import java.util.Date;

public class Article {
    private Integer AID;
    private String title;
    private String article;
    private Integer userID;
    private Date date;
    private int open;

    public Integer getAID() {
        return AID;
    }

    public void setAID(Integer AID) {
        this.AID = AID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Article{" +
                "AID=" + AID +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", userID='" + userID + '\'' +
                ", date=" + date +
                ", open=" + open +
                '}';
    }
}