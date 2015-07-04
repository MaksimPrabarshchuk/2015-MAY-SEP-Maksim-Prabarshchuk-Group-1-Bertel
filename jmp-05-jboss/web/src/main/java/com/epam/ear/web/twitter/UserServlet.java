package com.epam.ear.web.twitter;

import com.epam.ear.twitter.beans.UserBeanLocal;
import com.epam.ear.twitter.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {

    @EJB
    private UserBeanLocal userBeanLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userBeanLocal.list();
        request.setAttribute("users", users);
        request.getRequestDispatcher("WEB-INF/jsps/users.jsp").forward(request, response);
    }

}
