package kursovabd.controller;

import kursovabd.Repository.TariffPlanRepository;
import kursovabd.Repository.UserRepository;
import kursovabd.Repository.OrdersRepository;
import kursovabd.model.Users;
import kursovabd.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

@Controller
public class ConnectInternetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffPlanRepository tariffPlanRepository;

    @Autowired
    private OrdersRepository zayavkaRepository;

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/connectInternet/{userId}")
    public String showConnectInternetForm(@PathVariable Long userId, Model model) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("tariffPlans", tariffPlanRepository.findAll());
        }
        return "connectInternet";
    }



    @PostMapping("/connectInternet/{userId}")
    public String submitConnectionRequest(@PathVariable("userId") Long userId) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Orders zayavka = new Orders();
            zayavka.setUser(user);
            zayavka.setSubmissionDate(new Date());
            zayavka.setStatus("Successful");

            // Проверяем, есть ли у пользователя уже подключенный тарифный план
            if (user.getTariffPlans() != null) {
                zayavka.setTariffPlans(user.getTariffPlans());
            } else {
                zayavka.setTariffPlans(null);
            }

            zayavkaRepository.save(zayavka);
        }
        return "redirect:/Users/" + userId + "/cabinet";
    }
}