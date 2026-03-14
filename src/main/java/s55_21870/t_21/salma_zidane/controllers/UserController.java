package s55_21870.t_21.salma_zidane.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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
    private NoteService noteService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id) {
        User deletedUser = userService.getUserById(id); // throws 404 if not found
        userService.deleteUser(id);
        return deletedUser; // returns 200 OK with deleted user JSON
    }


    @GetMapping("/search")
    public User searchByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{id}/notes")
    public List<Note> getNotesByUser(@PathVariable String id){
        return noteService.getNotesByUserId(id);

    }

}
