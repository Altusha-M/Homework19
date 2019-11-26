package myApp;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This servlet shows all files, folders and everything in these folders in the specific directory
 * To start this servlet, please enter 'http://server-ip:8080/Homework19/
 * To specify directory on server, add getDirs?reqDir='your directory'
 */
@WebServlet("/getDirs")
public class App extends HttpServlet {

    @EJB(beanName = "MyBean")
    MyEJBean javaBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Wrong request");
        javaBean = new MyEJBean();
        String dir = request.getParameter("reqDir");
        dir = dir == null ? "." : dir;
        ArrayList<String> answer = javaBean.getDir(dir);
        request.setAttribute("answer", answer);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("application.jsp");
        rd.forward(request, response);
    }
}