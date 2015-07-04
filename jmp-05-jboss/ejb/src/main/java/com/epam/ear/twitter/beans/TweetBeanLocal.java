package com.epam.ear.twitter.beans;

import com.epam.ear.twitter.domain.Tweet;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TweetBeanLocal {

    public void save(Tweet tweet);

    public List<Tweet> list(int id);

    public List<Tweet> list(String username);

    public void save(String username, String text);

}
