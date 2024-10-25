package saikat.test.restapi.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound extends ResponseStatusException {

    public UserNotFound() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }

}
