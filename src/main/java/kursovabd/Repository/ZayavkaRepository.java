package kursovabd.Repository;

import kursovabd.model.Zayavka;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ZayavkaRepository extends CrudRepository<Zayavka, Long> {

    // Метод для поиска заявок по пользователю
    List<Zayavka> findByUser_Id(Long userId);

    // Метод для поиска заявок по статусу
    List<Zayavka> findByStatus(Integer status);

    // Метод для поиска заявок по тарифному плану
    List<Zayavka> findByTariffPlans_Id(Long tariffPlanId);

    // Метод для поиска заявок по дате подачи
    List<Zayavka> findBySubmissionDateBetween(Date startDate, Date endDate);

    // Пользовательский запрос для поиска заявок по статусу и тарифному плану
    @Query("SELECT z FROM Zayavka z WHERE z.status = :status AND z.tariffPlans.id = :tariffPlanId")
    List<Zayavka> findByStatusAndTariffPlan(@Param("status") Integer status, @Param("tariffPlanId") Long tariffPlanId);
}
