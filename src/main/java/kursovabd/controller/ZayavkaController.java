package kursovabd.controller;

import kursovabd.model.Zayavka;
import kursovabd.Repository.ZayavkaRepository;
import kursovabd.Repository.UserRepository;
import kursovabd.Repository.TariffPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ZayavkaController {

    @Autowired
    private ZayavkaRepository zayavkaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffPlanRepository tariffPlanRepository;

    @GetMapping("/zayavka")
    public String showZayavkas(Model model) {
        Iterable<Zayavka> zayavkas = zayavkaRepository.findAll();
        model.addAttribute("zayavkas", zayavkas);
        return "zayavka";
    }

    @PostMapping("/zayavka/delete/{id}")
    public String deleteZayavka(@PathVariable("id") Long id) {
        zayavkaRepository.deleteById(id);
        return "redirect:/zayavka";
    }

    @GetMapping("/zayavka/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Zayavka zayavka = zayavkaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid zayavka ID: " + id));
        model.addAttribute("zayavka", zayavka);
        return "editZayavka";
    }

    @PostMapping("/zayavka/update/{id}")
    public String updateZayavka(@PathVariable("id") Long id, @ModelAttribute("zayavka") Zayavka updatedZayavka) {
        Zayavka zayavka = zayavkaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid zayavka ID: " + id));

        zayavka.setStatus(updatedZayavka.getStatus());
        zayavkaRepository.save(zayavka);
        return "redirect:/zayavka";
    }
}