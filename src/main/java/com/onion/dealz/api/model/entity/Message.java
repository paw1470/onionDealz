package com.onion.dealz.api.model.entity;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User userFrom;

    @Column(name = "is_fromDeleted")                  //zakonczona z jakiegos powodu
    private boolean isFromDeleted;

    @JoinColumn(name = "user_to")        //do usera
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User userTo;

    @Column(name = "is_toDeleted")                  //zakonczona z jakiegos powodu
    private boolean isToDeleted;

    @Column(name = "send_date")             //kiedy wyslano
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
}
