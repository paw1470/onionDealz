package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        return entityManager.createNamedQuery("@GET_ALL_USERS").getResultList();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAllByName(String name) {
        return null;
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
