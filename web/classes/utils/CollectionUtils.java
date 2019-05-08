package classes.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {
    public Map<String, String> toRecognizableMap(Map<String, String[]> parameters) {
        //设定key值
        Set<String> parName = parameters.keySet();
        Map<String, String> userInf = new HashMap<>();
        for (String param : parName) {
            String[] value = parameters.get(param);
            userInf.put(param, Arrays.asList(value).get(0));
            System.out.println(param + ":" + Arrays.asList(value));
        }
        System.out.println(userInf);

        return userInf;

    }

}
