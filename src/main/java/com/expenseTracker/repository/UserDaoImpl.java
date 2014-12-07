package com.expenseTracker.repository;

import com.expenseTracker.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        User userFromDb = (User) sessionFactory.getCurrentSession().load(User.class, user.getId());
        if (userFromDb != null) {
            sessionFactory.getCurrentSession().delete(userFromDb);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findUser(int id) {
        List<User> users = sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.id = :userId")
                .setParameter("userId", id).list();

        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersOfExpenseSheet(int expenseSheetId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.expenseSheet.id = :expenseSheetId")
                .setParameter("expenseSheetId", expenseSheetId)
                .list();
    }
}