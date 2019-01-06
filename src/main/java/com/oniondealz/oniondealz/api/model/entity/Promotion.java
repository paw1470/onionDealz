package com.oniondealz.oniondealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "regular_price")
    private double regularPrice;

    @Column(name = "shipping_price")
    private double shippingPrice;

    @ElementCollection
    @CollectionTable(name = "cupons", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> cupons;

    @Column(name = "link")
    private String link;

    @JoinColumn(name = "user")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @Column(name = "add_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "is_ended")
    private boolean isEnded;

    @OneToMany(mappedBy = "coments")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "coments")
    private Set<User> likes;

    @OneToMany(mappedBy = "coments")
    private Set<User> unlikes;

    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> tags;

    @ElementCollection
    @CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> photos;

    @Column(name = "is_local")
    private boolean isLocal;

    @JoinColumn(name = "shop")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Shop shop;
}
