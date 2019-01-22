package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.utils.DateUtils;
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

    @Column(name = "text")
    private String text;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<User> likes;

    @Column(name = "add_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public void update(CommentUpdateDto com){
        DateUtils dateUtils = new DateUtils();
        this.setText(com.getText());
        this.setModifyDate(dateUtils.getCurrentDateTime());
    }

    public void addLike(User user){
        for(User u:likes){
            if(u.getId() == user.getId()){
                return;
            }
        }
        likes.add(user);
    }

    public void removeLike(User user){
        likes.remove(user);
    }
}
