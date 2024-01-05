package com.example.Media.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long instead of String for the id field

    @Column(name = "mot_de_passe")
    private String mdp;
    private String nom;
    private String email;
    private boolean actif = false;

    @ManyToOne(cascade = CascadeType.ALL) // Use ManyToOne for Role relationship
    private Role role;

    @ManyToOne
    @JoinColumn(name = "friend_list_id")
    private FriendList friendList;

  @ManyToMany
  private List<Post> savedBy = new ArrayList<>();







  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.getLibelle()));
    }

    @Override
    public String getPassword() {
        return this.mdp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }

    // Méthode setMdp manuellement
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
