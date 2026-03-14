package s55_21870.t_21.salma_zidane.models;
import java.util.UUID;
import java.time.LocalDateTime;

public class User {
    private String id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public User(){

    }

    public User(String username, String email){
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public User(String id,String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
