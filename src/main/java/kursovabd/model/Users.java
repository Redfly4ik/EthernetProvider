package kursovabd.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    private Long id;

    @Column(name = "Email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "Date_register", nullable = false, columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateRegister;

    @PrePersist
    protected void onCreate() {
        dateRegister = new Date(); // Устанавливаем текущую дату и время при сохранении нового объекта
    }

    @Column(name = "Adress", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "Number_phone", nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(name = "login", nullable = false, columnDefinition = "TEXT")
    private String login;

    @Column(name = "First_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "Second_name", nullable = false, columnDefinition = "TEXT")
    private String secondName;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_tariff_plans", referencedColumnName = "id_tariff_plans")
    private Tariff_plans tariffPlans;

    @OneToMany(mappedBy = "user")
    private List<Payments> payments;

    @OneToMany(mappedBy = "user")
    private List<Services> services;


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tariff_plans getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(Tariff_plans tariffPlans) {
        this.tariffPlans = tariffPlans;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
}
