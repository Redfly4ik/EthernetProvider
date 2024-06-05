package kursovabd.model;
import javax.persistence.*;

@Entity
@Table(name = "tariff_plans")
public class Tariff_plans {

    @Id
    @Column(name = "id_tariff_plans")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "cost", nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private Double cost;

    @Column(name = "speed", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer speed;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
