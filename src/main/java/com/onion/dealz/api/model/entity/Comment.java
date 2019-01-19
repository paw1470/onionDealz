package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.CommentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)          //lista userow lubiacych komentarz
    private Set<User> likes;

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
