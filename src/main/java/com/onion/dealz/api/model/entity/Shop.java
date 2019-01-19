package com.onion.dealz.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "name", length = 50)         //nazwa sklepu
    private String name;

    @Column(name = "link")                      //link jezeli ma gdzies strone
    private String link;

    @Column(name = "country")                      //link jezeli ma gdzies strone
    private String country;
}
