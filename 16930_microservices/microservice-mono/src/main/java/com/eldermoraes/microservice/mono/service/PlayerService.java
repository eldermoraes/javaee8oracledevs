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
package com.eldermoraes.microservice.mono.service;

import com.eldermoraes.microservice.mono.beans.PlayerBean;
import com.eldermoraes.microservice.mono.entities.Player;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("playerService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerService {
    
    @EJB
    private PlayerBean playerBean;
    
    @GET
    @Path("findById/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(playerBean.findById(id)).build();
    }
    
    @GET
    @Path("findAll")
    public Response findAll(){
        return Response.ok(playerBean.findAll()).build();
    }
    
    @POST
    public Response save(Player player){
        playerBean.save(player);
        return Response.accepted().build();
    }
    
    @DELETE
    public Response remove(Player player){
        playerBean.remove(player);
        return Response.accepted().build();
    }
}
