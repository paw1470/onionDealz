package com.oniondealz.oniondealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(name = "description")               //opis usera jak ktos chce dodac
    private String description;

    @Column(name = "photo")                     //link do zdjecia na innej stronie
    private String photo;

    @OneToMany(mappedBy = "coments")            //lista wpisanych komentarzy
    private Set<Comment> comments;

    @OneToMany(mappedBy = "promotions")         //lista polubionych promocji
    private Set<Promotion> liked;

    @OneToMany(mappedBy = "promotions")         //lista nielubianych promocji
    private Set<Promotion> unliked;

    @OneToMany(mappedBy = "comments")           //lista polubionych komentarzy
    private Set<Promotion> likedComments;

    @ElementCollection                          //lista otrzymanych wiadomosci
    @CollectionTable(name = "messages_received", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> messagesReceived;

    @ElementCollection                          //lista wyslanych wiadomosci
    @CollectionTable(name = "messages_send", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> messagesSend;

    @OneToMany(mappedBy = "events")             //eventy (do przemyslenia)
    private Set<Event> events;
}