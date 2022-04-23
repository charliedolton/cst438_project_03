package com.project3.easel;

import com.project3.easel.database.User;
import com.project3.easel.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class EaselApplication {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EaselApplication.class, args);
    }

    @GetMapping("/user-data")
    public String userdata(Model model){
        model.addAttribute("user", new User());
        return "user-data";
    }
    @PostMapping("/user-data")
    public String userdata(Model model, User user, @RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password ){
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userRepository.save(user);
        return "home";
    }

}
