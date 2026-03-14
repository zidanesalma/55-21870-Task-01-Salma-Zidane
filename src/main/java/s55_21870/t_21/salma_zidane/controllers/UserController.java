package s55_21870.t_21.salma_zidane.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import s55_21870.t_21.salma_zidane.models.Note;
import s55_21870.t_21.salma_zidane.models.User;
import s55_21870.t_21.salma_zidane.services.NoteService;
import s55_21870.t_21.salma_zidane.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final NoteService noteService;

    public UserController(UserService userService, NoteService NoteService noteService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    // 1. GET /users — returns all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 2. GET /users/{id} — returns a single user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // 3. POST /users — accepts JSON without id and returns created user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // 4. PUT /users/{id} — updates user and returns modified user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // 5. DELETE /users/{id} — removes user and returns nothing
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    // 6. GET /users/search?username=... — case-insensitive search
    @GetMapping("/search")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{id}/notes")
    public List<Note> getNotesByUserId(@PathVariable String id) {
        return noteService.getNotesByUserId(id);
    }

}
