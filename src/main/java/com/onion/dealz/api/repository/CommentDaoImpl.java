package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@NamedQueries({
        @NamedQuery(name = "@GET_ALL_COMMENTS", query = "FROM Comment"),
        @NamedQuery(name = "@GET_COMMENTS_BY_USER_ID", query = "FROM Comment WHERE user.id =: id"),
        @NamedQuery(name = "@GET_COMMENTS_BY_PROMOTION_ID", query = "FROM Comment WHERE promotion.id =: id"),
        @NamedQuery(name = "@GET_USERS_LIKED_COMMENT", query = "FROM User WHERE promotion.id =: id")        //todo nie wiem jakie zapytanie
})
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
        return (List<Comment>) entityManager.createNamedQuery("@GET_COMMENTS_BY_USER_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Comment> findAllByPromotionId(Long id) {
        return (List<Comment>) entityManager.createNamedQuery("@GET_COMMENTS_BY_PROMOTION_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<User> findAllLikes(Long id) {
        return (List<User>) entityManager.createNamedQuery("@GET_USERS_LIKED_COMMENT")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        entityManager.remove(findById(comment.getId()));
    }

    @Override
    public void updateComment(CommentUpdateDto comment) {
        Comment oldComment = findById(comment.getId());
        oldComment.update(comment);
        entityManager.flush();
    }

    @Override
    public void addLike(Comment comment, User user) {
        Comment tempComment = findById(comment.getId());
        tempComment.addLike(user);
        entityManager.flush();
    }

    @Override
    public void removeLike(Comment comment, User user) {
        Comment tempComment = findById(comment.getId());
        tempComment.removeLike(user);
        entityManager.flush();
    }


}
