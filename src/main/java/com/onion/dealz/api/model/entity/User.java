package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "@GET_ALL_USERS", query = "FROM User")
})
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "login", length = 20)        //to nie wymaga komentarza
    private String login;

    @Column(name = "password")                  //tu bedzie haslo
    private String password;

    @Column(name = "level")                     //level zdobywany przez dobrze oceniane promocje, admin to jakis wysoki numer ktorego nie mozna zdobyc normalnie
    private int level;

    @Column(name = "is_admin")                  //czy jest adminem
    private boolean isAdmin;

    @Column(name = "description")               //opis usera jak ktos chce dodac
    private String description;

    @Column(name = "photo")                     //link do zdjecia na innej stronie
    private String photo;

    public void update(UserDto userDto){
        this.description = userDto.getDescription();
        this.photo = userDto.getPhoto();
    }

    public void levelUp(int value){
        level += value;
    }

    public void levelDown(int value){
        level -= value;
    }

    public void clearLVL(){
        level = 0;
    }
}