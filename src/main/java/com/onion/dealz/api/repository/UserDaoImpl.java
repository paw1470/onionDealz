package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public List<User> findAllByName(String name) {
        return entityManager.createNamedQuery("@GET_ALL_USERS_BY_NAME").
                setParameter("name", name).
                getResultList();
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.flush();
    }

    @Override
    public User findByName(String name) {
        List<User> users = entityManager.createNamedQuery("@GET_USER_BY_NAME").
                setParameter("name", name).
                getResultList();
        for(User u: users){
            if(u.getLogin().equals(name)){
                return u;
            }
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
