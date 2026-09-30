package com.blinkchat.chat.lobby.entity;

import com.blinkchat.chat.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true , nullable=false, length=6)
    private String lobbyId;
    @Column(nullable = false)
    @Min(2)
    @Max(20)
    private Integer lobbySize;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @OneToMany(mappedBy = "lobby" ,  cascade = CascadeType.ALL , orphanRemoval = true)
    private List<User> users;
    @Column(nullable = false)
    private Integer currentUsers = 0;;

    public Lobby() {}

    public Integer getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(Integer currentUsers) {
        this.currentUsers = currentUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public Integer getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(Integer lobbySize) {
        this.lobbySize = lobbySize;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiredAt) {
        this.expiresAt = expiredAt;
    }
}
