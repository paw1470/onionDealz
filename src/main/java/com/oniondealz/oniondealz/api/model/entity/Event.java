package com.oniondealz.oniondealz.api.model.entity;

import com.oniondealz.oniondealz.api.model.MessageEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="message_event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageEventType eventType;

    @Column(name = "is_read")
    private boolean isRead;

    private User user;           //if user write or like,

    private Message message;    //if received message

    private Comment comment;    //if received comment, like on comment

    private Promotion promotion;    //new promotion, promotion received

    private String info;            //text for user
}
