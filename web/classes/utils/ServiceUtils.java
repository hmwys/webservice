package classes.utils;

public class ServiceUtils {
    public int modifyArticleOpenStatus(int openStatus){
        if (openStatus==0){
            return 1;
        }else return 0;
    }
}
