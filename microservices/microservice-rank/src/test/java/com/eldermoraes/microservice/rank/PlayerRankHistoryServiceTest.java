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
package com.eldermoraes.microservice.rank;

import com.eldermoraes.microservice.rank.entities.PlayerRankHistory;
import com.eldermoraes.microservice.rank.service.PlayerRankHistoryService;
import java.util.Date;
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
public class PlayerRankHistoryServiceTest {

    private EJBContainer ejbContainer;

    @EJB
    private PlayerRankHistoryService rankService;

    public PlayerRankHistoryServiceTest() {
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
    public void saveRankHistory() {
        PlayerRankHistory r = new PlayerRankHistory(1L, 1L, 10L, 1000L, new Date());
        Response response = rankService.save(r);
        assertEquals(202, response.getStatus());

        response = rankService.findAll();
        List<PlayerRankHistory> list = (List<PlayerRankHistory>) response.getEntity();
        assertEquals(1, list.size());
    }

    @Test
    public void findAllRankHistory() {
        PlayerRankHistory r = new PlayerRankHistory(1L, 1L, 10L, 1000L, new Date());
        rankService.save(r);

        Response response = rankService.findAll();
        List<PlayerRankHistory> list = (List<PlayerRankHistory>) response.getEntity();
        assertEquals(1, list.size());
    }

    @Test
    public void removeRankHistory() {
        PlayerRankHistory r = new PlayerRankHistory(1L, 1L, 10L, 1000L, new Date());
        rankService.save(r);

        Response response = rankService.remove(r.getId());
        assertEquals(202, response.getStatus());

        response = rankService.findAll();
        List<PlayerRankHistory> list = (List<PlayerRankHistory>) response.getEntity();
        assertEquals(0, list.size());
    }
}
