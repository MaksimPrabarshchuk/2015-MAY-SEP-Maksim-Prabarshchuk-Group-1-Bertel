package com.epam.mentoring.jboss.test;

import com.epam.ear.twitter.beans.UserBeanRemote;
import com.epam.ear.twitter.domain.User;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class RemoteUnitTest extends Assert {

    @Test
    public void test() throws NamingException {
        UserBeanRemote userBeanRemote = getUserBeanRemote();
        int size = userBeanRemote.list().size();
        User user = new User("test");
        userBeanRemote.save(user);
        assertTrue(size < userBeanRemote.list().size());
    }

    public UserBeanRemote getUserBeanRemote() throws NamingException {
        return (UserBeanRemote) getContext().lookup("myEjb-1.0-SNAPSHOT/UserBeanImpl!com.epam.ear.twitter.beans.UserBeanRemote");
    }

    public Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        p.put("jboss.naming.client.ejb.context", true);
        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
        p.put(InitialContext.SECURITY_PRINCIPAL, "myuser");
        p.put(InitialContext.SECURITY_CREDENTIALS, "mypass");
        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        final Context context = new InitialContext(p);
        return context;
    }

}
