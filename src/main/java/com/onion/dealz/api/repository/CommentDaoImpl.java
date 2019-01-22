package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAllComments() {
        return entityManager.createNamedQuery("@GET_ALL_COMMENTS").getResultList();
    }

    @Override
    public Comment findById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAllByUserId(Long id) {
        return entityManager.createNamedQuery("@GET_COMMENTS_BY_USER_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Comment> findAllByPromotionId(Long id) {
        return entityManager.createNamedQuery("@GET_COMMENTS_BY_PROMOTION_ID")
                .setParameter("id", id)
                .getResultList();
    }

//    @Override
//    public List<User> findAllLikes(Long id) {
//        return entityManager.createNamedQuery("@GET_USERS_LIKED_COMMENT")
//                .setParameter("id", id)
//                .getResultList();
//    }

    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        entityManager.remove(findById(comment.getId()));
    }

    @Override
    public void updateComment(Comment comment) {
        entityManager.flush();
    }
}
