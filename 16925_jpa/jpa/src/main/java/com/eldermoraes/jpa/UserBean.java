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

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    @PersistenceContext(unitName = "jpa-PU")
    private EntityManager em;
    
    public User add(User user){
        em.persist(user);
        em.flush();
        return user;
    }
    
    public void remove(User user){
        em.remove(findById(user.getId()));
    }
    
    public void update(User user){
        em.merge(user);
    }

    public User findById(Integer id){
        return em.find(User.class, id);
    }
    
    public User findByName(String name){
        try{
            return (User)em.createNamedQuery("User.findByName").setParameter("name", name).getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    public List<User> findAll(){
        return em.createNamedQuery("User.findAll").getResultList();
    }
}
