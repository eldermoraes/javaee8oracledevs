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
package com.eldermoraes.microservice.gateway;

import com.eldermoraes.microservice.gateway.dto.PlayerRankHistory;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("rankGateway")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RankGateway {
    
    private final String hostURI = "http://localhost:8080/";
    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target(hostURI + "microservice-rank/");
    }    
    
    @GET
    @Path("findById")
    public Response findById(@PathParam("id") Long id) {
        WebTarget service = target.path("resources/playerRankHistoryService/findById/" + id);

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

        return Response.ok(response).build();
    }  
    
    @GET
    @Path("findAll")
    public Response findAll() {
        WebTarget service = target.path("resources/playerRankHistoryService/findAll/");

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

        return Response.fromResponse(response).build();
    }  
    
    @POST
    public Response save(PlayerRankHistory rank) {
        WebTarget service = target.path("resources/playerRankHistoryService");

        Response response;
        try {
            response = service.request().post(Entity.json(rank));
        } catch (ProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

        return Response.fromResponse(response).build();
    }   
    
    @DELETE
    @Path("{id}")
    public Response remove(Long id) {
        WebTarget service = target.path("resources/playerRankHistoryService/" + id);

        Response response;
        try {
            response = service.request().delete();
        } catch (ProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

        return Response.fromResponse(response).build();
    }    
}
