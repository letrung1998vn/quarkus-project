package com.baeldung.quarkus;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;
@Path("")
public class HelloResource {

    AccountDAO dao;

    public HelloResource(AccountDAO dao) {
        this.dao = dao;
    }

    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<String> hello(@QueryParam("userName") String userName, @QueryParam("password")String password) {
        AccountDTO dto = dao.findByUsernameAndPassword(userName, password);
        if (dto != null) {
            return RestResponse.ResponseBuilder.ok("Welcome " + userName, MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        }
    }
}
