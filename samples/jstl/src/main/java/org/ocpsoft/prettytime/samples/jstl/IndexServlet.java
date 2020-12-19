package org.ocpsoft.prettytime.samples.jstl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet
{

    private static final long serialVersionUID = 3698098463023956514L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("myDate", new Date());
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
