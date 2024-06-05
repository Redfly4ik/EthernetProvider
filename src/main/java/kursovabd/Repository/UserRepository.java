package kursovabd.Repository;

import kursovabd.model.UserTariffInfo;
import kursovabd.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Метод для поиска пользователя по логину
    Users findByLogin(String login);
    // Метод для поиска пользователя по email
    Users findByEmail(String email);

    // Метод для поиска пользователей по имени
    List<Users> findByFirstName(String firstName);

    // Метод для поиска пользователей по фамилии
    List<Users> findBySecondName(String secondName);

    // Метод для поиска пользователей по тарифному плану
    List<Users> findByTariffPlans_Id(Long tariffPlanId);

    // Метод для поиска пользователей, зарегистрированных в определенном диапазоне дат
    @Query("SELECT new kursovabd.model.UserTariffInfo(u.id, u.firstName, u.secondName, u.email, t.id, t.name, t.cost, t.speed) " +
            "FROM Users u LEFT JOIN u.tariffPlans t")
    List<UserTariffInfo> findUserTariffs();








}