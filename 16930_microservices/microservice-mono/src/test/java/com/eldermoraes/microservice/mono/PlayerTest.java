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
package com.eldermoraes.microservice.mono;

import com.eldermoraes.microservice.mono.beans.PlayerBean;
import com.eldermoraes.microservice.mono.entities.Player;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eldermoraes
 */
public class PlayerTest {
    
    private EJBContainer ejbContainer;    
    
    @EJB
    private PlayerBean playerBean;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() throws NamingException {
        Properties p = new Properties();
        p.put("microDb", "new://Resource?type=DataSource");
        p.put("microDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("microDb.JdbcUrl", "jdbc:hsqldb:mem:microdatabase");

        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);        
    }
    
    @After
    public void tearDown() {
        ejbContainer.close();
    }
    
    @Test
    public void save(){
        Player p = new Player(1L,"Player 1", 10L);
        playerBean.save(p);
        
        assertEquals(1, playerBean.findAll().size());
    }
    
    @Test
    public void remove(){
        Player p = new Player(1L,"Player 1", 10L);
        playerBean.save(p);
        playerBean.remove(p);
        
        assertEquals(0, playerBean.findAll().size());
    }    
}
