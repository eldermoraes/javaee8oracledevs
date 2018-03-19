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
package com.eldermoraes.microservice.mono.beans;

import com.eldermoraes.microservice.mono.entities.PlayerRankHistory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class PlayerRankHistoryBean {

    @PersistenceContext(unitName = "micro-PU")
    private EntityManager em;
    
    public void save(PlayerRankHistory rank){
        em.merge(rank);
    }
    
    public void remove(PlayerRankHistory rank){
        em.remove(findById(rank.getId()));
    }
    
    public PlayerRankHistory findById(Long id){
        return em.find(PlayerRankHistory.class, id);
    }
    
    public List<PlayerRankHistory> findAll(){
        return em.createQuery("SELECT r FROM PlayerRankHistory r").getResultList();
    }
    
}
