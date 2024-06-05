package kursovabd.controller;
import kursovabd.Repository.UserRepository;
import kursovabd.model.UserTariffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userTariffs")
    public String getUserTariffs(Model model) {
        List<UserTariffInfo> userTariffs = userRepository.findUserTariffs();
        model.addAttribute("userTariffs", userTariffs);

        return "userTariffs";
    }
}