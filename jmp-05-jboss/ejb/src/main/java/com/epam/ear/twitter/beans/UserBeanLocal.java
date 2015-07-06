package com.epam.ear.twitter.beans;

import com.epam.ear.twitter.domain.User;

import javax.ejb.Local;
import java.util.List;
@Local
public interface UserBeanLocal {

    public void save(User user);

    public void delete(User user);

    public List<User> list();

}
