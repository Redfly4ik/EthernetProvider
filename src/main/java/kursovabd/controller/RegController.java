package kursovabd.controller;

import kursovabd.Repository.UserRepository;
import kursovabd.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegController {

    private final UserRepository userRepository;

    @Autowired
    public RegController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        userRepository.save(user);
        return "redirect:/login";
    }
}
