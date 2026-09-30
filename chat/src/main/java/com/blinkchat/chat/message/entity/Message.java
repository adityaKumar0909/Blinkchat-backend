package com.blinkchat.chat.message.entity;

import com.blinkchat.chat.lobby.entity.Lobby;
import com.blinkchat.chat.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(nullable = false, length = 500)
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;

    @ManyToOne
    private Lobby lobby;

    // Getters and setters


    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}