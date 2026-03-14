package s55_21870.t_21.salma_zidane.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import s55_21870.t_21.salma_zidane.models.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository {

    private java.io.File jsonFile;
    private final List<User> users;

    public UserRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/users.json");
        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read users.json");
        }
        try {
            this.jsonFile = new java.io.File(getClass().getResource("/users.json").toURI());
        } catch (Exception e) {
            this.jsonFile = new java.io.File("/data/users.json");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        this.users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
        });
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public User save(User user) {
        User newUser = new User(user.getUsername(), user.getEmail());
        users.add(newUser);
        new ObjectMapper().writeValue(jsonFile, users);
        return newUser;
    }

    public Optional<User> update(String id, User updated) {
        Optional<User> targetUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        targetUser.ifPresent(user -> {
            user.setUsername(updated.getUsername());
            user.setEmail(updated.getEmail());

        });

        new ObjectMapper().writeValue(jsonFile, users);

        return targetUser;
    }

    public boolean deleteById(String id) {
        Optional<User> targetUser = users.stream()
                .filter(user -> user.getUsername().equals(id))
                .findFirst();

        if (targetUser.isPresent()) {
            users.remove(targetUser.get());
            new ObjectMapper().writeValue(jsonFile, users);
            return true;
        }
        return false;
    }

}
