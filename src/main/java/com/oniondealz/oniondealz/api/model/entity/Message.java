package com.oniondealz.oniondealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "text")              //tekst wiadomosci
    private String text;

    @JoinColumn(name = "user_from")     //od usera
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User userFrom;

    @JoinColumn(name = "user_to")        //do usera
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User userTo;

    @Column(name = "send_date")             //kiedy wyslano
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
}
