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

    @Column(name = "text")
    private String text;

    @JoinColumn(name = "user_from")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User userFrom;

    @Column(name = "is_fromDeleted")
    private boolean isFromDeleted;

    @JoinColumn(name = "user_to")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User userTo;

    @Column(name = "is_toDeleted")
    private boolean isToDeleted;

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
}
