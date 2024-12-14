package kursovabd.controller;

import kursovabd.model.Orders;
import kursovabd.Repository.OrdersRepository;
import kursovabd.Repository.UserRepository;
import kursovabd.Repository.TariffPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffPlanRepository tariffPlanRepository;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        Iterable<Orders> orders = ordersRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders/delete/{id}")
    public String deleteOrders(@PathVariable("id") Long id) {
        ordersRepository.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid orders ID: " + id));
        model.addAttribute("orders", orders);
        return "editOrders";
    }

    @PostMapping("/orders/update/{id}")
    public String updateOrders(@PathVariable("id") Long id, @ModelAttribute("orders") Orders updatedOrders) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid orders ID: " + id));

        orders.setStatus(updatedOrders.getStatus());
        ordersRepository.save(orders);
        return "redirect:/orders";
    }
}