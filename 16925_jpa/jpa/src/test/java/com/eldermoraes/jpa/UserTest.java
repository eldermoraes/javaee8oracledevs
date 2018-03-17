/*
 * Copyright 2018 eldermoraes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eldermoraes.jpa;

import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author eldermoraes
 */
public class UserTest {

    private EJBContainer ejbContainer;

    @EJB
    private UserBean userBean;

    public UserTest() {
    }

    @Before
    public void setUp() throws NamingException {
        Properties p = new Properties();
        p.put("userDb", "new://Resource?type=DataSource");
        p.put("userDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("userDb.JdbcUrl", "jdbc:hsqldb:mem:userdatabase");

        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
    }

    @After
    public void tearDown() {
        ejbContainer.close();
    }

    @Test
    public void add() {
        User user = new User("User", "user@test.com");

        userBean.add(user);
        Assert.assertEquals(1, userBean.findAll().size());
    }

    @Test
    public void update() {
        User user = new User("User", "user@test.com");
        user = userBean.add(user);
        user.setName("User1");
        userBean.update(user);

        Assert.assertNotNull(userBean.findByName("User1"));
    }

    @Test
    public void remove() {
        User user = new User("User", "user@test.com");
        user = userBean.add(user);
        
        userBean.remove(user);

        Assert.assertEquals(0, userBean.findAll().size());
    }

}
