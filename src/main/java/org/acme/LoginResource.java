package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dao.AccountDAO;
import org.acme.dto.AccountDTO;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Base64;

@Path("/auth")
public class LoginResource {

    AccountDAO dao;

    public LoginResource(AccountDAO dao) {
        this.dao = dao;
    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (Character.isSpaceChar(ch)) {
                return false;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public RestResponse<String> login(@RestForm String userName, @RestForm String password) {
        String encodePass = new String(Base64.getEncoder().encode(password.getBytes()));
        if (password.length() <= 8 || password.length() >= 16 || !checkString(password)) {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Invalid password format").build();
        }

        AccountDTO dto = dao.findByUsernameAndPassword(userName, encodePass);
        if (dto != null) {
            return RestResponse.ResponseBuilder.ok("Welcome " + userName, MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            return RestResponse.ResponseBuilder.create(RestResponse.Status.BAD_REQUEST, "Incorrect username or password").build();
        }
    }
}
