package kursovabd.Repository;
import kursovabd.projection.TariffPlanZvit;
import kursovabd.model.Tariff_plans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TariffPlanRepository extends CrudRepository<Tariff_plans, Long> {

    // Метод для поиска тарифных планов по стоимости
    List<Tariff_plans> findByCost(Double cost);

    // Метод для поиска тарифных планов по скорости
    List<Tariff_plans> findBySpeed(Integer speed);

    // Метод для поиска тарифных планов по скорости
    Tariff_plans findByName(String name);

    @Query(value = "WITH revenue_per_plan AS (" +
            "    SELECT " +
            "        tp.name AS tariffPlanName, " +
            "        COUNT(u.id_users) AS totalUsers, " +
            "        SUM(tp.cost) AS totalRevenue " +
            "    FROM " +
            "        Users u " +
            "    JOIN " +
            "        tariff_plans tp ON u.id_tariff_plans = tp.id_tariff_plans " +
            "    GROUP BY " +
            "        tp.name " +
            ") " +
            "SELECT " +
            "    tariffPlanName, " +
            "    totalUsers, " +
            "    totalRevenue, " +
            "    SUM(totalRevenue) OVER () AS TotalRevenueSum " +
            "FROM " +
            "    revenue_per_plan;", nativeQuery = true)
    List<TariffPlanZvit> getTariffPlanZvit();
}