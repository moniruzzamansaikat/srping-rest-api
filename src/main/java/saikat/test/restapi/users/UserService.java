package saikat.test.restapi.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user, Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        user = new User(
                null,
                user.name(),
                user.username() != null ? user.username() : null,
                user.email(),
                user.dateOfBirth(),
                user.password(),
                user.phoneNumber(),
                user.isEmailVerified(),
                user.isTwoFactorEnabled(),
                user.securityQuestion(),
                user.securityAnswer(),
                user.lastLogin()
        );
        return userRepository.save(user);
    }
}
