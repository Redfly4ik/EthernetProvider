package kursovabd.controller;

import kursovabd.model.Tariff_plans;
import kursovabd.Repository.TariffPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TariffPlanController {

    @Autowired
    private TariffPlanRepository tariffPlanRepository;

    @GetMapping("/Tariff_Plans")
    public String showTariffPlans(Model model) {
        Iterable<Tariff_plans> tariffPlans = tariffPlanRepository.findAll();
        model.addAttribute("tariffPlans", tariffPlans);
        return "tariff_plans";
    }

    @DeleteMapping("/Tariff_Plans/delete/{id}")
    public String deleteTariffPlan(@PathVariable("id") Long id) {
        tariffPlanRepository.deleteById(id);
        return "redirect:/Tariff_Plans";
    }

    @GetMapping("/tariff_Plans/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Tariff_plans tariffPlan = tariffPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tariff plan ID: " + id));
        model.addAttribute("tariffPlan", tariffPlan);
        return "editTariffPlan";
    }

    @PostMapping("/tariff_Plans/update/{id}")
    public String updateTariffPlan(@PathVariable("id") Long id, @ModelAttribute("tariffPlan") Tariff_plans updatedTariffPlan, RedirectAttributes redirectAttributes) {
        Optional<Tariff_plans> existingOptional = tariffPlanRepository.findById(id);
        if (!existingOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid tariff plan ID: " + id);
        }
        Tariff_plans existingTariffPlan = existingOptional.get();

        // Update the existing tariff plan with the new data, except for the ID
        existingTariffPlan.setName(updatedTariffPlan.getName());
        existingTariffPlan.setCost(updatedTariffPlan.getCost());
        existingTariffPlan.setSpeed(updatedTariffPlan.getSpeed());

        // Save the updated tariff plan
        tariffPlanRepository.save(existingTariffPlan);
        redirectAttributes.addFlashAttribute("message", "Tariff plan updated successfully!");

        return "redirect:/Tariff_Plans";
    }
    @PostMapping("/tariff_Plans/add")
    public String addTariffPlan(
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "cost", defaultValue = "0") double cost,
                                @RequestParam(value = "speed", defaultValue = "0") int speed,
                                RedirectAttributes redirectAttributes) {
        Tariff_plans newTariffPlan = new Tariff_plans();

        newTariffPlan.setName(name);
        newTariffPlan.setCost(cost);
        newTariffPlan.setSpeed(speed);

        tariffPlanRepository.save(newTariffPlan);

        redirectAttributes.addFlashAttribute("message", "Tariff plan added successfully!");
        return "redirect:/Tariff_Plans";
    }
}