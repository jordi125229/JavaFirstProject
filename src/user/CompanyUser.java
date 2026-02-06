package user;

public class CompanyUser extends User {
    private String companyName;

    public CompanyUser(String email, String companyName) {
        super(email, companyName);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
