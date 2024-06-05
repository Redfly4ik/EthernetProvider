package kursovabd.controller;

import kursovabd.Repository.UserRepository;
import kursovabd.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
public class UserCabinetController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/Users/{id}/cabinet")
    public String showUserCabinet(@PathVariable("id") Long id, Model model) {
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "erro1r"; // Assuming you have an erro1r.html template to show errors
        }
        model.addAttribute("user", user);
        return "userCabinet"; // Assuming you have a userCabinet.html template to show the user cabinet
    }
}
