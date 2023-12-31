package com.Integration.NTI.Models.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public String getUserName() {
        return userName;
    }

    @Column(nullable = false, unique = true)
    private String userName;
    @NotEmpty
    private String password;

    boolean isAdmin;

    public User(String username, String password, Set<Role> roles) {
        this.userName = username;
        this.password = password;
        this.roles = roles;
    }

    public User(){
        this.roles = new HashSet<>();
    }

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference

    private Cart cart;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public void setUserName(String email) {
        this.userName = email;
    }



    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
