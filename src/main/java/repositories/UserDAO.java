package repositories;

import models.User;

public interface UserDAO {
    User getUserGivenUsername(String username);
    void crateUser(User user);
}
