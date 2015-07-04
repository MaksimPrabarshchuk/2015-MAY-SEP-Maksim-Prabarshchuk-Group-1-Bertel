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

@WebServlet(description = "Add User Servlet", urlPatterns = {"/addUsers"})
public class AddUserServlet extends HttpServlet {

    private static final String NAME = "name";

    @EJB
    private UserBeanLocal userBeanLocal;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME);
        userBeanLocal.save(new User(name));
        response.sendRedirect("users");
    }

}
