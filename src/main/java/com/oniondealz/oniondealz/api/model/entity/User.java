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

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "level")
    private int level;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "coments")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "promotions")
    private Set<Promotion> liked;

    @OneToMany(mappedBy = "promotions")
    private Set<Promotion> unliked;

    @OneToMany(mappedBy = "comments")
    private Set<Promotion> likedComments;

    @ElementCollection
    @CollectionTable(name = "messages_received", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> messagesReceived;

    @ElementCollection
    @CollectionTable(name = "messages_send", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> messagesSend;

    @OneToMany(mappedBy = "events")
    private Set<Event> events;
}