package com.epam.ear.twitter.beans.impl;

import com.epam.ear.twitter.beans.TweetBeanLocal;
import com.epam.ear.twitter.beans.TweetBeanRemote;
import com.epam.ear.twitter.domain.Tweet;
import com.epam.ear.twitter.domain.User;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@DeclareRoles("testrole")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TweetBeanImpl implements TweetBeanLocal, TweetBeanRemote {

    @PersistenceContext
    private EntityManager em;

    @RolesAllowed("testrole")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Tweet tweet) {
        em.persist(tweet);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Tweet> list(int id) {
        Query q = em.createQuery("from Tweet where author.id = :authorId", Tweet.class);
        q.setParameter("authorId", id);
        return q.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Tweet> list(String username) {
        Query q = em.createQuery("from Tweet where author.name = :username");
        q.setParameter("username", username);
        List<Tweet> r =  q.getResultList();
        for(Tweet t : r) {
            System.out.println(t.getText());
        }
        return r;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(String username, String text) {
        Query q = em.createQuery("from UserTable where name = :username");
        q.setParameter("username", username);

        User user = (User) q.getSingleResult();
        Tweet tweet = new Tweet(text, user);

        em.persist(tweet);
    }

}
