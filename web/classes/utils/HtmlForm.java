package classes.utils;

import classes.Bean.Article;
import org.apache.commons.text.StringEscapeUtils;

public class HtmlForm {
    public String openStatus(Integer openStatus) {

        if (openStatus == 1) {
            return "隐藏文章";
        } else return "显示文章";

    }

    public String toPara(String body) {
        return body.replaceAll("\\\n", "<br>");
    }

    public String escHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }

    public String oprationHtml(Article article) {
        return "<div style=\"text-align: center\"><a href=\"setStatus?AID=" + article.getAID() +
                "&openStatus=" + article.getOpen() +
                "\" >" + openStatus(article.getOpen()) +
                "</a></div><br>";
    }

    public String articlesAreEmpty() {
        return "<h3 class=\"article\" style=\"text-align: center\"><br><br><br>这里什么都没有...<br><br><br><br><br><br></h3>";
    }

    public String htImgHead(String body){
        return body.replaceAll("HtpiC","<img class=\"articles\" src=\"");
    }

    public String htImgEnd(String body) {
        return body.replaceAll("HtpicE","\">");
    }

    private String htStrongHead(String body){
        return body.replaceAll("HtstronG","<div class=\"strongp\"><strong>");
    }

    private String htStrongEnd(String body){
        return body.replaceAll("HtstrongE","</strong></div>");
    }

    public String completeText(String body){
        String s=toPara(body);
        s=htImgHead(s);
        s=htImgEnd(s);
        s=htStrongHead(s);
        s=htStrongEnd(s);
        return s;
    }
}
