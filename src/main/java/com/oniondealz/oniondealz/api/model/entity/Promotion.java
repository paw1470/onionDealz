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

    @Column(name = "title", length = 50)        //tytul promocji
    private String title;

    @Column(name = "description")                //opis promocji
    private String description;

    @Column(name = "price")                     //aktualna cena
    private double price;

    @Column(name = "regular_price")             //normalna cena
    private double regularPrice;

    @Column(name = "shipping_price")            //koszt wysylki
    private double shippingPrice;

    @ElementCollection                          //kupony (zakładam że może być kilka do jednego produktu)
    @CollectionTable(name = "cupons", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> cupons;

    @Column(name = "link")                      //link do oferty/sklepu
    private String link;

    @JoinColumn(name = "user")                  //kto dodal
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @Column(name = "add_date")                  //kiedy dodal
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")               //ostatnia modyfikacja
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "start_date")                //data rozpoczecia promocji (mozna wstawic wczesniej)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")                  //data zakonczenia promocji(nic nie trwa wiecznie)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "is_ended")                  //zakonczona z jakiegos powodu
    private boolean isEnded;

    @OneToMany(mappedBy = "coments")            //lista komentarzy
    private Set<Comment> comments;

    @OneToMany(mappedBy = "coments")            //lista osob ktore polubily promocje
    private Set<User> likes;

    @OneToMany(mappedBy = "coments")            //lista osob ktore nie lubia promocji
    private Set<User> unlikes;

    @ElementCollection                          //lista tagow (do przemyslenia jak to zrobic)
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> tags;

    @ElementCollection                          //lista linkow do zdjec(zakladam ze kazde zdjecie bedzie linkiem do zewnetrznej strony)
    @CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "id"))
    private ArrayList<String> photos;

    @Column(name = "is_local")                  //czy promocja lokalna
    private boolean isLocal;

    @JoinColumn(name = "shop")                  //sklep w ktorm jest promocja
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Shop shop;
}
