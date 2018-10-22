package by.itacademy;

import by.itacademy.model.User;
import by.itacademy.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {

    private User user = UserDao.getInstance().getDefaultUser();

    @Test
    public void checkDefaultUserName() {
        String actualResult = user.getName();
        String expectedResult = "Natallia";
        Assert.assertEquals("Default user name is wrong", actualResult, expectedResult);
    }
}
