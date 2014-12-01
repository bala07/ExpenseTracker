package service;

import com.expenseTracker.domain.User;
import com.expenseTracker.repository.UserDao;
import com.expenseTracker.service.UserService;
import com.expenseTracker.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTests {
    @Mock
    private UserDao userDao;

    private UserService userService;

    @Before
    public void setup() {
        initMocks(this);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void shouldAddUser() {
        User user = new User();

        userService.addUser(user);

        verify(userDao).addUser(user);
    }

    @Test
    public void shouldUpdateUser() {
        User user = new User();

        userService.updateUser(user);

        verify(userDao).updateUser(user);
    }

    @Test
    public void shouldDeleteUser() {
        User user = new User();

        userService.deleteUser(user);

        verify(userDao).deleteUser(user);
    }

    @Test
    public void shouldFindUserById() {
        User expectedUser = new User();
        expectedUser.setId(1);

        when(userDao.findUser(1)).thenReturn(expectedUser);

        User user = userService.findUser(1);

        assertThat(user, is(expectedUser));
    }

    @Test
    public void shouldFindAllUsers() {
        userService.findAllUsers();

        verify(userDao).findAllUsers();
    }


}
