package classes.utils;

import classes.Bean.Article;

public class HtmlForm {
    public String openStatus(Integer openStatus) {

        if (openStatus == 1) {
            return "隐藏文章";
        } else return "显示文章";

    }

    public String toPara(String body) {
        String body1;
        body1 = setImgAttr(body);
        body1=body1.replaceAll("\\n","<br></p><p>");
        return body1.replaceAll("\\n", "<br>");
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

    private String setImgAttr(String body){
        return body.replaceAll("<img","<img class=\"articles\" ");
    }




}
