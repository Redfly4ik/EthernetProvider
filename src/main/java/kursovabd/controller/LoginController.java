package kursovabd.controller;

import kursovabd.Repository.UserRepository;
import kursovabd.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Users user, Model model) {
        // Найти пользователя по логину
        Users foundUser = userRepository.findByLogin(user.getLogin());

        // Проверка на null
        if (foundUser != null) {
            // Проверка для администратора
            if ("admin".equals(user.getLogin()) && foundUser.getPassword().equals(user.getPassword())) {
                return "redirect:/admin";
            }
            // Проверка для других пользователей
            if (foundUser.getPassword().equals(user.getPassword())) {
                return "redirect:/Users/" + foundUser.getId() + "/cabinet";
            }
        }

        // Если данные не прошли проверку
        model.addAttribute("user", user);
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}