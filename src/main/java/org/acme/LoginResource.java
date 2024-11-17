package org.acme;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.bean.LoginBean;
import org.acme.dao.AccountDAO;
import org.acme.dto.AccountDTO;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/auth")
public class LoginResource {

    AccountDAO dao;

    public LoginResource(AccountDAO dao) {
        this.dao = dao;
    }


    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<String> login(@NotNull @Valid LoginBean login) {
        AccountDTO dto = dao.findByUsernameAndPassword(login.getUserName());
        if (dto == null) {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        } else {
            if (BcryptUtil.matches(login.getPassword(), dto.getPassword())) {
                return RestResponse.ResponseBuilder.ok("Welcome " + dto.getUserName(), MediaType.TEXT_PLAIN_TYPE).build();
            }
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        }
    }
}
