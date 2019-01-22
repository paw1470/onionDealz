package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "@GET_ALL_PROMOTIONS", query = "FROM Promotion"),
        @NamedQuery(name = "@GET_PROMOTIONS_BY_USER_ID", query = "FROM Promotion WHERE user.id =: id"),
        @NamedQuery(name = "@GET_PROMOTIONS_BY_SHOP_ID", query = "FROM Promotion WHERE shop.id =: id")
//        @NamedQuery(name = "@GET_USERS_LIKED_PROMOTION", query = "FROM User WHERE promotion.id =: id")        //todo nie wiem jakie zapytanie
})
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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "add_date")                  //kiedy dodal
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")               //ostatnia modyfikacja
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "start_date")                //data rozpoczecia promocji (mozna wstawic wczesniej)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")                  //data zakonczenia promocji(nic nie trwa wiecznie)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToMany(cascade = { CascadeType.ALL})           //lista osob ktore polubily promocje
    private List<User> likes;

    @ManyToMany(cascade = { CascadeType.ALL})             //lista osob ktore nie lubia promocji
    private List<User> unlikes;

    @ManyToMany(cascade = { CascadeType.ALL})             //lista osob ktore nie lubia promocji
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

    public void update(PromotionDto promotionDto) {
        DateUtils dateUtils = new DateUtils();
        this.title = promotionDto.getTitle();
        this.description = promotionDto.getDescription();
        this.price = promotionDto.getPrice();
        this.regularPrice = promotionDto.getRegularPrice();
        this.shippingPrice = promotionDto.getShippingPrice();
        this.cupon = promotionDto.getCupon();
        this.link = promotionDto.getLink();
        this.modifyDate = dateUtils.getCurrentDateTime();
        this.startDate = dateUtils.stringToDate(promotionDto.getStartDate());
        this.endDate = dateUtils.stringToDate(promotionDto.getEndDate());
        this.isActive = promotionDto.isActive();
        this.isLocal = promotionDto.isLocal();
        this.shopAddress = promotionDto.getShopAddress();
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
