package user;

public class CompanyUser extends User {
    private String companyName;
    private String taxId;

    public CompanyUser(String email, String companyName, String taxId) {
        super(email, companyName);
        this.taxId = taxId;
    }

    @Override
    public String toString() {
        return "CompanyUser: " + getDisplayName() + ", taxID: " + taxId + ", email: " + getEmail();
    }
}
