package com.expenseTracker.repository;

import com.expenseTracker.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDaoTest {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session currentSession;

    private UserDao userDao;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        userDao = new UserDaoImpl(sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(currentSession);
    }

    @Test
    public void shouldAddUser() {
        User user = new User();
        userDao.addUser(user);

        verify(currentSession).save(user);
    }

    @Test
    public void shouldUpdateUser() {
        User user = new User();

        userDao.updateUser(user);

        verify(currentSession).update(user);
    }

    @Test
    public void shouldDeleteUserIfUserIsPresent() {
        User user = new User();
        user.setId(1);

        when(currentSession.load(User.class, 1)).thenReturn(user);

        userDao.deleteUser(user);

        verify(currentSession).delete(user);
    }

    @Test
    public void shouldNotDeleteUserIfUserIsNotPresent() {
        User user = new User();

        when(currentSession.load(User.class, 1)).thenReturn(null);

        userDao.deleteUser(user);

        verify(currentSession, never()).delete(user);
    }

    @Test
    public void shouldReturnUserGivenId() {
        Query query = mock(Query.class);
        User expectedUser = new User();
        expectedUser.setId(1);
        List userList = newArrayList(expectedUser);

        when(currentSession.createQuery("from User u where u.id = :userId")).thenReturn(query);
        when(query.setParameter("userId", 1)).thenReturn(query);
        when(query.list()).thenReturn(userList);

        User user = userDao.findUser(1);

        assertThat(user, is(expectedUser));
    }

    @Test
    public void shouldReturnNullIfUserWithGivenIdNotPresent() {
        Query query = mock(Query.class);

        when(currentSession.createQuery("from User u where u.id = :userId")).thenReturn(query);
        when(query.setParameter("userId", 1)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList());

        User user = userDao.findUser(1);

        assertThat(user, is(nullValue()));
    }

    @Test
    public void shouldReturnAllUsers() {
        Query query = mock(Query.class);
        User user = new User();
        List<User> expectedUsers = newArrayList(user);

        when(currentSession.createQuery("from User")).thenReturn(query);
        when(query.list()).thenReturn(expectedUsers);

        List<User> users = userDao.findAllUsers();

        assertThat(users, is(expectedUsers));
    }
}
