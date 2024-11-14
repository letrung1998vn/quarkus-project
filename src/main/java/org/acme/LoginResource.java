package org.acme;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;
import org.acme.bean.LoginBean;
import org.acme.dao.AccountDAO;
import org.acme.dto.AccountDTO;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/auth")
public class LoginResource {

    AccountDAO dao;

    public LoginResource(AccountDAO dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        System.out.println(BcryptUtil.bcryptHash("Admin12345"));
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<String> login(@NotNull @Valid LoginBean login) {
        List<AccountDTO> dto = dao.findByUsernameAndPassword(login.getUserName());
        if (dto.isEmpty()) {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        } else {
            for (AccountDTO account : dto) {
                if(BcryptUtil.matches(login.getPassword(),account.getPassword())){
                    return RestResponse.ResponseBuilder.ok("Welcome " + account.getUserName(), MediaType.TEXT_PLAIN_TYPE).build();
                }
            }
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        }
    }
}
