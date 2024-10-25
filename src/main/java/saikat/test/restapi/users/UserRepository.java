package saikat.test.restapi.users;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> findAll() {
        return users;
    }

    public Optional<User> findOne(Integer id) {
        return users.stream().filter(u -> Objects.equals(u.id(), id)).findFirst();
    }

    public User save(User user) {
        users.add(user);

        return user;
    }

    public User update(User user, Integer id) {
        Optional<User> existingUserOpt = findOne(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            User updatedUser = new User(
                    id,
                    user.name() != null ? user.name() : existingUser.name(),
                    user.username() != null ? user.username() : existingUser.username(),
                    user.email()),
                    user.dateOfBirth().orElse(existingUser.dateOfBirth()),
                    user.password().orElse(existingUser.password()),
                    user.phoneNumber().orElse(existingUser.phoneNumber()),
                    user.isEmailVerified(),
                    user.isTwoFactorEnabled(),
                    user.securityQuestion().orElse(existingUser.securityQuestion()),
                    user.securityAnswer().orElse(existingUser.securityAnswer()),
                    user.lastLogin().orElse(existingUser.lastLogin())
            );
            // Update the user in the list
            users.remove(existingUser);
            users.add(updatedUser);
            return updatedUser;
        }
        
        throw new UserNotFound("User with ID " + id + " not found");
    }

    // this will create some dummy data for now when init the repo
    @PostConstruct
    public void init() {
        users.add(new User(1, "Moniruzzaman Saikat", "moniruzzamansaikat", "saikat.moniruzzaman@gmail.com"));
        users.add(new User(2, "Moni", "moniruzzamansaikat", "moni@gmail.com"));
        users.add(new User(3, "Pattrict Collison", "pattrick", "pattrick@gmail.com"));
    }

}
