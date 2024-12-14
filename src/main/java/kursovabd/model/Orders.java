package kursovabd.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private Users user;

    @Column(name = "date", nullable = false, columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date submissionDate;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_tariff_plans")
    private Tariff_plans tariffPlans;
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tariff_plans getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(Tariff_plans tariffPlans) {
        this.tariffPlans = tariffPlans;
    }
}
