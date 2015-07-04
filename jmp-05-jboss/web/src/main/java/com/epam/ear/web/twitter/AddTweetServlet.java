package com.epam.ear.web.twitter;

import com.epam.ear.twitter.beans.TweetBeanLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Add User Servlet", urlPatterns = {"/addTweet"})
public class AddTweetServlet extends HttpServlet {

    private static final String TEXT = "text";

    @EJB
    private TweetBeanLocal tweetBeanLocal;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter(TEXT);
        String name = (String) request.getSession().getAttribute("username");
        tweetBeanLocal.save(name, text);
        response.sendRedirect("tweets");
    }

}
