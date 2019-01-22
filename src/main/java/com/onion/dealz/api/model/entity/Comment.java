package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.CommentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "@GET_ALL_COMMENTS", query = "FROM Comment"),
        @NamedQuery(name = "@GET_COMMENTS_BY_USER_ID", query = "FROM Comment WHERE user.id =: id"),
        @NamedQuery(name = "@GET_COMMENTS_BY_PROMOTION_ID", query = "FROM Comment WHERE promotion.id =: id")
//        @NamedQuery(name = "@GET_USERS_LIKED_COMMENT", query = "FROM User WHERE likes.id =: id")        //todo nie wiem jakie zapytanie
})
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Promotion promotion;

    @Column(name = "text")                  //tekst komentarza
    private String text;

    @ManyToMany(cascade = { CascadeType.ALL})            //lista userow lubiacych komentarz
    private List<User> likes;

    @Column(name = "add_date")              //data dodania
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")           //data ostatniej modyfikacji
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public void update(CommentUpdateDto com){
        this.setText(com.getText());
        this.setModifyDate(com.getModifyDate());
    }

    public void addLike(User user){
        likes.add(user);
    }

    public void removeLike(User user){
        likes.remove(user);
    }
}
