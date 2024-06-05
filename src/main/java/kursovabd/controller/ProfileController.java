package kursovabd.controller;
import kursovabd.Repository.UserRepository;
import kursovabd.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Метод для отображения формы редактирования данных пользователя
// Метод для отображения формы редактирования данных пользователя
    @GetMapping("/editProfile/{userId}")
    public String showEditProfileForm(@PathVariable Long userId, Model model) {
        // Получение пользователя по его идентификатору из репозитория
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            // Если пользователь найден, передаем его в модель для отображения в форме
            model.addAttribute("user", optionalUser.get());
            return "editProfile"; // Вероятно, у вас есть представление с именем editProfile.html
        } else {
            // Если пользователь не найден, выполните соответствующие действия
            // Например, перенаправление на страницу с сообщением об ошибке или другую обработку
            return "erro1r"; // Вероятно, у вас есть представление с именем erro1r.html
        }
    }

    @PostMapping("/updateProfile/{id}")
    public String processUpdateProfileForm(@PathVariable("id") Long userId, @ModelAttribute("user") Users updatedUser) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Обновление данных пользователя
            user.setEmail(updatedUser.getEmail());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setFirstName(updatedUser.getFirstName());
            user.setSecondName(updatedUser.getSecondName());

            // Сохранение обновленных данных пользователя в базе данных
            userRepository.save(user);
        }
        return "redirect:/Users/" + userId + "/cabinet";
    }
}