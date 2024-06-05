package kursovabd.controller;

import kursovabd.Repository.PaymentRepository;
import kursovabd.Repository.TariffPlanRepository;
import kursovabd.Repository.UserRepository;
import kursovabd.model.Payments;
import kursovabd.model.Tariff_plans;
import kursovabd.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SubmitOrderController {

    @Autowired
    private TariffPlanRepository tariffPlanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/submitOrder/{userId}")
    public String showSubmitOrderForm(@PathVariable Long userId, Model model) {
        // Получение данных пользователя из базы данных по userId и добавление их в модель
        Users user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);

        // Ваша логика для получения списка тарифных планов и добавления их в модель
        Iterable<Tariff_plans> tariffPlans = tariffPlanRepository.findAll();
        model.addAttribute("tariffPlans", tariffPlans);

        return "submitOrder";
    }
    @PostMapping("/submitOrder/{userId}")
    public String submitOrder(@PathVariable Long userId, @RequestParam("tariffPlansName") String tariffPlanName,
                              @RequestParam("paymentMethod") String paymentMethod,
                              @RequestParam("numberCard") String numberCard) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/error?message=User+not+found";
        }

        Tariff_plans tariffPlan = tariffPlanRepository.findByName(tariffPlanName);
        if (tariffPlan == null) {
            return "redirect:/error?message=Tariff+plan+not+found";
        }
        // Create a payment object
        Payments payment = new Payments();
        payment.setStatus("Successful");
        payment.setUser(user);
        payment.setDate(new Date());
        payment.setSum(tariffPlan.getCost());
        payment.setPaymentMethod(paymentMethod);
        payment.setNumberCard(numberCard); // Установка номера карты

        // Save the payment to the database
        paymentRepository.save(payment);

        // Update user's tariff plan
        user.setTariffPlans(tariffPlan);
        userRepository.save(user);

        // Redirect to user cabinet
        return "redirect:/Users/" + userId + "/cabinet";
    }

}