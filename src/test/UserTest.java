package test;

import user.CompanyUser;
import user.IndividualUser;

class UserTest {
    public static void main(String[] args) {
        CompanyUser user1 = new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
        IndividualUser user2 = new IndividualUser("elo_krzychu@gmail.com", "krzych");

        System.out.println(user1);
        System.out.println(user2);
    }
}
