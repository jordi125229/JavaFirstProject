package repository;

import user.User;

import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users;
    private int userCount = 0;

    @Override
    public void add(User u) {
        users.add(u);
        userCount++;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        if (userCount == 0) {
            System.out.println("No resources");
        }
        return users;
    }
}
