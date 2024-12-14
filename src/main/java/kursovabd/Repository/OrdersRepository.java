package kursovabd.Repository;

import kursovabd.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

    // Метод для поиска заявок по пользователю
    List<Orders> findByUser_Id(Long userId);

    // Метод для поиска заявок по статусу
    List<Orders> findByStatus(Integer status);

    // Метод для поиска заявок по тарифному плану
    List<Orders> findByTariffPlans_Id(Long tariffPlanId);

    // Метод для поиска заявок по дате подачи
    List<Orders> findBySubmissionDateBetween(Date startDate, Date endDate);

    // Пользовательский запрос для поиска заявок по статусу и тарифному плану
    @Query("SELECT z FROM Orders z WHERE z.status = :status AND z.tariffPlans.id = :tariffPlanId")
    List<Orders> findByStatusAndTariffPlan(@Param("status") Integer status, @Param("tariffPlanId") Long tariffPlanId);
}
