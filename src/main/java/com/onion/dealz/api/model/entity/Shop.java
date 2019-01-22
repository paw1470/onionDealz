package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.ShopDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "@GET_ALL_SHOPS", query = "FROM Shop"),
        @NamedQuery(name = "@GET_SHOP_BY_NAME", query = "FROM Shop WHERE name =: name"),
        @NamedQuery(name = "@GET_ALL_SHOPS_BY_NAME", query = "FROM Shop WHERE name LIKE CONCAT('%',:name,'%')")
})
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;

    public void update(ShopDto shopDto){
        this.name = shopDto.getName();
        this.link = shopDto.getLink();
        this.country = shopDto.getCountry();
        this.description = shopDto.getDescription();
    }
}
