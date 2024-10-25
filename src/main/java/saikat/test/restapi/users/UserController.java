package saikat.test.restapi.users;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.findOne(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @PostMapping({"", "/"} )
    public User newUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}/update")
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        return userService.update(user, id);
    }

}