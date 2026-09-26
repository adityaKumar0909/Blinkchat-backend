package com.blinkchat.chat.lobby.dto;

import java.time.LocalDateTime;

public class CreateLobbyResponse {
    private  String lobbyId;
    private  LocalDateTime expiresAt;
    private Integer lobbySize;

    public CreateLobbyResponse(){
    }

    public CreateLobbyResponse(String lobbyId, LocalDateTime expiresAt, Integer lobbySize) {
        this.lobbyId = lobbyId;
        this.expiresAt = expiresAt;
        this.lobbySize = lobbySize;
    }

    public Integer getLobbySize() {

        return lobbySize;
    }

    public void setLobbySize(Integer lobbySize) {
        this.lobbySize = lobbySize;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
