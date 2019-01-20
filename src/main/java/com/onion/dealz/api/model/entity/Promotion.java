package com.onion.dealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    @Column(name = "cupon")            //koszt wysylki
    private String cupon;

    @Column(name = "link")                      //link do oferty/sklepu
    private String link;

    @JoinColumn(name = "user")                  //kto dodal
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @ManyToMany(fetch = FetchType.EAGER)           //lista osob ktore polubily promocje
    private List<User> likes;

    @ManyToMany(fetch = FetchType.EAGER)             //lista osob ktore nie lubia promocji
    private List<User> unlikes;

    @ManyToMany(fetch = FetchType.EAGER)             //lista osob ktore nie lubia promocji
    private List<User> observers;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_local")                  //czy promocja lokalna
    private boolean isLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Column(name = "shop_address")
    private String shopAddress;

//    @ElementCollection                          //lista tagow (do przemyslenia jak to zrobic)
//    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "id"))
//    private ArrayList<String> tags;
//
    @Column(name = "photo")
    private String photo;



    public void update(Promotion promotion) {
        this.title = promotion.getTitle();
        this.description = promotion.getDescription();
        this.price = promotion.getPrice();
        this.regularPrice = promotion.getRegularPrice();
        this.shippingPrice = promotion.getShippingPrice();
        this.link = promotion.getLink();
        this.modifyDate = promotion.getModifyDate();
        this.startDate = promotion.getStartDate();
        this.endDate = promotion.getEndDate();
        this.isActive = promotion.isActive();
        this.isLocal = promotion.isLocal();
        this.shopAddress = promotion.getShopAddress();
    }

    public void addUnlike(User user){
        removeLike(user);
        this.unlikes.add(user);
    }

    public void addLike(User user){
        removeUnlike(user);
        this.likes.add(user);
    }

    public void removeLike(User user){
        this.likes.remove(user);
    }

    public void removeUnlike(User user){
        this.unlikes.remove(user);
    }
}
