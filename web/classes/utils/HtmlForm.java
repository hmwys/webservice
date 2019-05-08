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

    public String toHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }

    public String oprationHtml(Article article) {
        return "<div style=\"text-align: center\"><a href=\"setStatus?AID=" + article.getAID() +
                "&openStatus="+article.getOpen()+
                "\" >" + openStatus(article.getOpen()) +
                "</a></div><br>";
    }

}
