package saikat.test.restapi.users;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", ""})
    public ArrayList<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findOne(id);
        return optionalUser.orElseThrow(() -> new UserNotFound());
    }

    @PostMapping({"", "/"})
    public User newUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping({"{id}/update", "/{id}/update"})
    public User newUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        return userRepository.update(user, id);
    }

}
