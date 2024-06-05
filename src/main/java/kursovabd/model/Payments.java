package kursovabd.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @Column(name = "id_payments")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_pay", nullable = false, columnDefinition = "TEXT")
    private String status;

    @Column(name = "date_payments", nullable = false, columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @Column(name = "Summa", nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private Double sum;

    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private Users user;

    @Column(name = "payment_method", nullable = false, columnDefinition = "TEXT")
    private String paymentMethod;

    @Column(name = "NumberCard", nullable = true, columnDefinition = "TEXT")
    private String numberCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }
}

