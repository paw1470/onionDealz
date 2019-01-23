package com.onion.dealz.api.model.entity;

import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.dto.UserPasswordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = "@GET_ALL_USERS", query = "FROM User"),
        @NamedQuery(name = "@GET_USER_BY_NAME", query = "FROM User WHERE login =: name"),
        @NamedQuery(name = "@GET_ALL_USERS_BY_NAME", query = "FROM User WHERE login LIKE CONCAT('%',:name,'%')")
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

    @Column(name = "login", length = 20, unique = true, nullable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "level")
    private int level;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_upgraded")
    private boolean isUpgraded;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    public void update(UserDto userDto){
        this.description = userDto.getDescription();
        this.photo = userDto.getPhoto();
    }

    public void modifyAccountType(boolean admin, boolean mod){
        isAdmin = admin;
        isUpgraded = mod;
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

    public boolean updatePassword(UserPasswordDto userPasswordDto) {
        if(login.equals(userPasswordDto.getLogin())){
            this.password = userPasswordDto.getPassword();
            return true;
        }
        return false;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (isAdmin) {
//            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else if(isUpgraded){
//            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_MOD"));
//        }
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getUsername() {
//        return this.login;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    
}