package org.ionescu.david.projweb.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("auth/login")
    User auth(@RequestParam String email, @RequestParam String password) {
        List<User> users = userRepository.findByEmailAndPassword(email, password);
        if (users.isEmpty()) {
            throw new AuthFailedException();
        }
        return users.get(0);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    List<User> findAll() {
        return userRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    User findById(@PathVariable Integer id) {
        Optional<User> e = userRepository.findById(id);
        if (e.isEmpty()) {
            throw new UserNotFoundException();
        }
        return e.get();
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody User body) {
        userRepository.save(body);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    void update(@Valid @PathVariable Integer id, @RequestBody User body) {
        Optional<User> u = userRepository.findById(id);
        userRepository.save(body);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("email/{email}")
    List<User> findByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

}
