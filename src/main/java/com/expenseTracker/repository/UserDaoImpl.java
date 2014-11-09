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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
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
    public List<User> findAllUsers() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User")
                .list();
    }
}