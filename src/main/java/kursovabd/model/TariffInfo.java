package kursovabd.model;

public class TariffInfo {
    private String tariffPlanName;
    private Long totalUsers;
    private Double totalRevenue;
    private Double totalRevenueSum;

    // Constructors, getters, and setters

    public TariffInfo(String tariffPlanName, Long totalUsers, Double totalRevenue, Double totalRevenueSum) {
        this.tariffPlanName = tariffPlanName;
        this.totalUsers = totalUsers;
        this.totalRevenue = totalRevenue;
        this.totalRevenueSum = totalRevenueSum;
    }

    public String getTariffPlanName() {
        return tariffPlanName;
    }

    public void setTariffPlanName(String tariffPlanName) {
        this.tariffPlanName = tariffPlanName;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalRevenueSum() {
        return totalRevenueSum;
    }

    public void setTotalRevenueSum(Double totalRevenueSum) {
        this.totalRevenueSum = totalRevenueSum;
}
}
