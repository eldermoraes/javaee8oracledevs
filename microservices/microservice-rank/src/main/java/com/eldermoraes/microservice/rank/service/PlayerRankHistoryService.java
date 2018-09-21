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
package com.eldermoraes.microservice.rank.service;

import com.eldermoraes.microservice.rank.beans.PlayerRankHistoryBean;
import com.eldermoraes.microservice.rank.entities.PlayerRankHistory;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("playerRankHistoryService")
public class PlayerRankHistoryService {
    
    @EJB
    private PlayerRankHistoryBean rankBean;
    
    
    @GET
    @Path("findById/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(rankBean.findById(id)).build();
    }
    
    @GET
    @Path("findAll")
    public Response findAll(){
        return Response.ok(rankBean.findAll()).build();
    }
    
    @POST
    public Response save(PlayerRankHistory rank){
        rankBean.save(rank);
        return Response.accepted().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(Long id){
        PlayerRankHistory rank = rankBean.findById(id);
        rankBean.remove(rank);
        return Response.accepted().build();
    }
}
