package saikat.test.restapi.users;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userService.delete(id);
    }
}