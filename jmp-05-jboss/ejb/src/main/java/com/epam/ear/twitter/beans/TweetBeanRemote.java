package com.epam.ear.twitter.beans;

import com.epam.ear.twitter.domain.Tweet;

import javax.ejb.Remote;

@Remote
public interface TweetBeanRemote {

    public void save(Tweet tweet);

    public void save(String username, String text);

}
