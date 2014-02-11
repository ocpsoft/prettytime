package org.ocpsoft.prettytime.samples.jstl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("myDate", new Date());
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
