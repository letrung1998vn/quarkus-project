package org.acme;

import org.acme.dao.AccountDAO;
import org.acme.dto.AccountDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("")
public class GreetingResource {

    AccountDAO dao;

    public GreetingResource(AccountDAO dao) {
        this.dao = dao;
    }

    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<String> login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        AccountDTO dto = dao.findByUsernameAndPassword(userName, password);
        if (dto != null) {
            return RestResponse.ResponseBuilder.ok("Welcome " + dto.getUserName(), MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        }
    }
}
