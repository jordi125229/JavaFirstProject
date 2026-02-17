package user;

public class IndividualUser extends User {

    public IndividualUser(String email, String displayName) {
        super(email, displayName);
    }

    @Override
    public String toString() {
        return "IndividualUser: " + getDisplayName() + ", email: " + getEmail();
    }
}
