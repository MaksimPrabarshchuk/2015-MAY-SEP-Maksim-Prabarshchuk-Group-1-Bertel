package com.epam.ear.twitter.beans;

import com.epam.ear.twitter.domain.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserBeanRemote {

    public void save(User user);

    public void delete(User user);

    public List<User> list();

}
