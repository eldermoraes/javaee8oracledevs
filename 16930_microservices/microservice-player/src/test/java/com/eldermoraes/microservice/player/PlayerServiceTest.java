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
package com.eldermoraes.microservice.player;

import com.eldermoraes.microservice.player.entities.Player;
import com.eldermoraes.microservice.player.service.PlayerService;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eldermoraes
 */
public class PlayerServiceTest {

    private EJBContainer ejbContainer;

    @EJB
    private PlayerService playerService;

    public PlayerServiceTest() {
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
    public void savePlayer() {
        Player p = new Player(1L, "Player1", 10L);
        Response response = playerService.save(p);
        assertEquals(202, response.getStatus());
        
        response = playerService.findAll();
        List<Player> list = (List<Player>) response.getEntity();
        assertEquals(1, list.size());
    }

    @Test
    public void findAllPlayers() {
        Player p = new Player(1L, "Player1", 10L);
        playerService.save(p);
        Response response = playerService.findAll();
        List<Player> list = (List<Player>) response.getEntity();
        assertEquals(1, list.size());
    }
    
    @Test
    public void removePlayer() {
        Player p = new Player(1L, "Player1", 10L);
        playerService.save(p);
        Response response = playerService.remove(p.getId());
        assertEquals(202, response.getStatus());
        
        response = playerService.findAll();
        List<Player> list = (List<Player>) response.getEntity();
        assertEquals(0, list.size());        
    }    
}
