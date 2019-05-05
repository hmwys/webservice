package classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获得单独的参数
        String name = request.getParameter("username");
        System.out.println("用户名：" + name);


        //获得多个参数
        String[] habitss = request.getParameterValues("hobits");
        System.out.println(Arrays.asList(habitss));


        Map<String, String[]> parameters = request.getParameterMap();
        //设定key值
        Set<String> parName = parameters.keySet();
        for (String param : parName) {
            String[] value = parameters.get(param);
            System.out.println(param + ":" + Arrays.asList(value));
        }

    }
}