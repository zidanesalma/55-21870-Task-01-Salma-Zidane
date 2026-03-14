package s55_21870.t_21.salma_zidane.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${USER_NAME}")
    private String userName;

    @Value("${ID}")
    private String id;

    // GET /welcome endpoint
    @GetMapping("/welcome")
    public String welcome() {
        return "Hello " + userName + " " + id + ", from Notes API";
    }

}
