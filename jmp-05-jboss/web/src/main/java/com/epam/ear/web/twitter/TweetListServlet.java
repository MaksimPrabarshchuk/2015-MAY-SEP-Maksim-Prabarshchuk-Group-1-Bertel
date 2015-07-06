package com.epam.ear.web.twitter;

import com.epam.ear.twitter.beans.TweetBeanLocal;
import com.epam.ear.twitter.domain.Tweet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/tweets"})
public class TweetListServlet extends HttpServlet {

    @EJB
    private TweetBeanLocal tweetBeanLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if(username == null || username.equals("")) {
            response.sendRedirect("loginForm");
            return;
        }
        List<Tweet> tweets = tweetBeanLocal.list(username);
        System.out.println("Tweets size" + tweets.size());
        request.setAttribute("tweets", tweets);
        request.getRequestDispatcher("WEB-INF/jsps/tweetsList.jsp").forward(request, response);
    }

}
