package kursovabd.model;
public class UserTariffInfo {
    private Long userId;
    private String firstName;
    private String secondName;
    private String email;
    private Long tariffPlanId;
    private String tariffPlanName;
    private Double cost;
    private Integer speed;

    // Конструктор
    public UserTariffInfo(Long userId, String firstName, String secondName, String email, Long tariffPlanId, String tariffPlanName, Double cost, Integer speed) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.tariffPlanId = tariffPlanId;
        this.tariffPlanName = tariffPlanName;
        this.cost = cost;
        this.speed = speed;
    }

    // Getters и Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(Long tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }

    public String getTariffPlanName() {
        return tariffPlanName;
    }

    public void setTariffPlanName(String tariffPlanName) {
        this.tariffPlanName = tariffPlanName;
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
