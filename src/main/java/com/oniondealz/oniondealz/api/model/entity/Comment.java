package com.oniondealz.oniondealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "text")                  //tekst komentarza
    private String text;

    @OneToMany(mappedBy = "users")          //lista userow lubiacych komentarz
    private Set<User> likes;

    @Column(name = "add_date")              //data dodania
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")           //data ostatniej modyfikacji
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
}
