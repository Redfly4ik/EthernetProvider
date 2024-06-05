package kursovabd.controller;

import kursovabd.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "index"; // указываем имя шаблона без расширения
    }

    @GetMapping("/")
    public String hello(Model model, HttpSession session) {
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        model.addAttribute("loggedInUser", loggedInUser);
        return "index"; // возвращаем шаблон главной страницы
    }


}