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

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "regular_price")
    private double regularPrice;

    @Column(name = "shipping_price")
    private double shippingPrice;

    @Column(name = "cupon")
    private String cupon;

    @Column(name = "link")
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "add_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<User> likes;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<User> unlikes;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<User> observers;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_local")
    private boolean isLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Column(name = "shop_address")
    private String shopAddress;

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
        for(User u:unlikes){
            if(u.getId() == user.getId()){
                return;
            }
        }
        this.unlikes.add(user);
    }

    public void addLike(User user){
        removeUnlike(user);
        for(User u:likes){
            if(u.getId() == user.getId()){
                return;
            }
        }
        this.likes.add(user);
    }

    public void removeLike(User user){
        this.likes.remove(user);
    }

    public void removeUnlike(User user){
        this.unlikes.remove(user);
    }
}
