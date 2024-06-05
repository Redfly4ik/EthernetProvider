package kursovabd.controller;


import kursovabd.Repository.UserRepository;
import kursovabd.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kursovabd.model.Payments;
import kursovabd.Repository.PaymentRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller

public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/payments/delete/{id}")
    public String deletePayment(@PathVariable("id") Integer id) {
        paymentRepository.deleteById(id);
        return "redirect:/payments";
    }

    @GetMapping("/payments/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Payments payment = paymentRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment ID: " + id));
        model.addAttribute("payment", payment);
        return "editPayment";
    }

    @GetMapping("/payments/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Payments payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment ID: " + id));

        model.addAttribute("payment", payment);
        return "updatePayment";
    }

    @PostMapping("/payments/update/{id}")
    public String updatePayment(@PathVariable("id") Integer id, @ModelAttribute("payment") Payments updatedPayment, RedirectAttributes redirectAttributes) {
        Optional<Payments> existingOptional = paymentRepository.findById(id);
        if (!existingOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid payment ID: " + id);
        }
        Payments existingPayment = existingOptional.get();

        existingPayment.setStatus(updatedPayment.getStatus());
        existingPayment.setSum(updatedPayment.getSum());
        existingPayment.setDate(updatedPayment.getDate() != null ? updatedPayment.getDate() : new java.util.Date());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());

        paymentRepository.save(existingPayment);
        redirectAttributes.addFlashAttribute("message", "Payment updated successfully!");

        return "redirect:/payments";
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/payments")
    public String showPayments(Model model) {
        Iterable<Payments> payments = paymentRepository.findAll();
        Iterable<Users> users = userRepository.findAll();
        model.addAttribute("payments", payments);
        model.addAttribute("users", users);
        return "payments";
    }

    @PostMapping("/payments/add")
    public String addPayment(@ModelAttribute Payments payment, @RequestParam("userId") Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        payment.setStatus(payment.getStatus() != null ? payment.getStatus() : "");
        payment.setSum(payment.getSum() != null ? payment.getSum() : 0.0);
        payment.setDate(payment.getDate() != null ? payment.getDate() : new java.util.Date());
        payment.setUser(user);

        paymentRepository.save(payment);

        return "redirect:/payments";
    }

    @GetMapping("/payments/filter")
    public String filterPayments(@RequestParam("statusFilter") String statusFilter, Model model) {
        List<Payments> filteredPayments = paymentRepository.findByStatus(statusFilter);
        Iterable<Users> users = userRepository.findAll();
        model.addAttribute("payments", filteredPayments);
        model.addAttribute("users", users);
        return "payments";

    }
}

