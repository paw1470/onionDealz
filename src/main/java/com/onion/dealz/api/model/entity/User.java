package com.onion.dealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "login", length = 20)        //to nie wymaga komentarza
    private String login;

    @Column(name = "password")                  //tu bedzie haslo
    private String password;

    @Column(name = "level")                     //level zdobywany przez dobrze oceniane promocje, admin to jakis wysoki numer ktorego nie mozna zdobyc normalnie
    private int level;

    @Column(name = "is_admin")                  //czy jest adminem
    private boolean isAdmin;

    @Column(name = "description")               //opis usera jak ktos chce dodac
    private String description;

    @Column(name = "photo")                     //link do zdjecia na innej stronie
    private String photo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")            //lista wpisanych komentarzy
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")        //lista dodanych promocji
    private Set<Promotion> addedPromotions;

    @ManyToMany(fetch = FetchType.LAZY)        //lista polubionych promocji
    private Set<Promotion> likedPromotions;

    @ManyToMany(fetch = FetchType.LAZY)       //lista nielubianych promocji
    private Set<Promotion> unlikedPromotions;

    @ManyToMany(fetch = FetchType.LAZY)       //lista polubionych komentarzy
    private Set<Comment> likedComments;

    @ManyToMany(fetch = FetchType.LAZY)                         //lista otrzymanych wiadomosci
    private Set<Message> messagesReceived;

    @ManyToMany(fetch = FetchType.LAZY)                          //lista wyslanych wiadomosci
    private Set<Message> messagesSend;

//    @OneToMany(mappedBy = "events")             //eventy (do przemyslenia)
//    private Set<Event> events;
}