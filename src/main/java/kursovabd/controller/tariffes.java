package kursovabd.controller;

import kursovabd.Repository.TariffPlanRepository;
import kursovabd.projection.TariffPlanZvit;
import kursovabd.projection.TariffPlanZvit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class tariffes {

    @Autowired
    private TariffPlanRepository tariffPlanRepository;

    @GetMapping("/Tariffes")
    public String getUserTariffes(Model model) {
        List<TariffPlanZvit> tariffZvit = tariffPlanRepository.getTariffPlanZvit();
        model.addAttribute("tariffZvit", tariffZvit);
        return "Tariffes";
    }
}
