package com.epam.ear.twitter.beans.impl;

import com.epam.ear.twitter.beans.UserBeanLocal;
import com.epam.ear.twitter.beans.UserBeanRemote;
import com.epam.ear.twitter.domain.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBeanImpl implements UserBeanLocal, UserBeanRemote {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(User user) {
        em.persist(user);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(User user) {
        em.remove(user);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<User> list() {
        Query q = em.createQuery("from UserTable");
        return q.getResultList();
    }

}
