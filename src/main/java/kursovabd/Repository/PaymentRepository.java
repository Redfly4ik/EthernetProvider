package kursovabd.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import kursovabd.model.Payments;
import java.util.List;
import java.util.Date;

@Repository
public interface PaymentRepository extends CrudRepository<Payments, Integer> {
    List<Payments> findByStatus(String status);

    List<Payments> findByPaymentMethod(String paymentMethod);

    // Метод для поиска оплат по пользователю
    List<Payments> findByUser_Id(Long userId);

    List<Payments> findByDateBetween(Date startDate, Date endDate);

    // Метод для поиска оплат по сумме
    List<Payments> findBySumGreaterThanEqual(Double sum);
}
