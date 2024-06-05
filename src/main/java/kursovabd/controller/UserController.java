package kursovabd.controller;
import kursovabd.Repository.TariffPlanRepository;
import kursovabd.model.Tariff_plans;
import kursovabd.model.Users;
import kursovabd.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffPlanRepository tariffPlanRepository; // Добавляем репозиторий тарифных планов

    @GetMapping("/Users")
    public String showUsers(Model model) {
        Iterable<Users> users = userRepository.findAll();
        Iterable<Tariff_plans> tariffPlans = tariffPlanRepository.findAll(); // Получаем все тарифные планы
        model.addAttribute("users", users);
        model.addAttribute("tariffPlans", tariffPlans); // Добавляем тарифные планы в модель
        return "users";
    }

    @PostMapping("/Users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/Users";
    }

    @GetMapping("/Users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Users user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/Users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") Users updatedUser, RedirectAttributes redirectAttributes) {
        if (!id.equals(updatedUser.getId()) && updatedUser.getId() != null) {
            // Check if the new ID is already in use
            boolean exists = userRepository.existsById(updatedUser.getId());
            if (exists) {
                redirectAttributes.addFlashAttribute("error", "A user with this ID already exists.");
                return "redirect:/Users/edit/" + id;
            }
            // Remove the old user
            userRepository.deleteById(id);
        }

        // Set the new ID (if changed) and update other fields
        Users user = new Users();
        user.setId(updatedUser.getId() != null ? updatedUser.getId() : id); // Use new ID or fall back to the old ID
        user.setEmail(updatedUser.getEmail());
        user.setDateRegister(updatedUser.getDateRegister() != null ? updatedUser.getDateRegister() : new java.util.Date());
        user.setAddress(updatedUser.getAddress());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setLogin(updatedUser.getLogin());
        user.setFirstName(updatedUser.getFirstName());
        user.setSecondName(updatedUser.getSecondName());
        user.setPassword(updatedUser.getPassword());

        // Check if the tariffPlans is set and exists in the database
        if (updatedUser.getTariffPlans() != null && updatedUser.getTariffPlans().getId() != null) {
            Optional<Tariff_plans> tariffPlansOptional = tariffPlanRepository.findById(updatedUser.getTariffPlans().getId());
            if (tariffPlansOptional.isPresent()) {
                user.setTariffPlans(tariffPlansOptional.get());
            } else {
                redirectAttributes.addFlashAttribute("error", "Tariff Plan not found.");
                return "redirect:/Users/edit/" + id;
            }
        } else {
            user.setTariffPlans(null); // Set to null if no valid Tariff Plan ID is provided
        }

        // Save the updated or new user
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        return "redirect:/Users";
    }

    @PostMapping("/Users/add")
    public String addUser(@ModelAttribute Users user, RedirectAttributes redirectAttributes) {
        Long selectedTariffPlanId = user.getTariffPlans() != null ? user.getTariffPlans().getId() : null;

        if (selectedTariffPlanId != null) {
            Tariff_plans tariffPlan = tariffPlanRepository.findById(selectedTariffPlanId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Tariff Plan ID: " + selectedTariffPlanId));
            user.setTariffPlans(tariffPlan);
        } else {
            user.setTariffPlans(null); // Set tariffPlans to null if no valid ID is provided
        }

        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "User added successfully!");
        return "redirect:/Users";
    }
}