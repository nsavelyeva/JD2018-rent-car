package by.itacademy.service;

import by.itacademy.dao.UserDao;
import by.itacademy.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User getDefaultUser() {
        return UserDao.getInstance().getDefaultUser();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
