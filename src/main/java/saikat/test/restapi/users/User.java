package saikat.test.restapi.users;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record User(
        Integer id,
        @NotEmpty String name,
        String username,
        @NotEmpty @Email String email,
        Optional<LocalDate> dateOfBirth,
        Optional<String> password,
        Optional<String> phoneNumber,
        boolean isEmailVerified,
        boolean isTwoFactorEnabled,
        Optional<String> securityQuestion,
        Optional<String> securityAnswer,
        Optional<LocalDate> lastLogin) {

    public User(Integer id, String name, String username, String email) {
        this(id, name, username, email, Optional.empty(), Optional.empty(), Optional.empty(), false, false, Optional.empty(), Optional.empty(), Optional.empty());
    }

}
