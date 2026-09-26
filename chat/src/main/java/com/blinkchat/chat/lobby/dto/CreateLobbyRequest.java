package com.blinkchat.chat.lobby.dto;

public class CreateLobbyRequest {
    private Integer expiryInHours;
    private Integer lobbySize = 20;

     public CreateLobbyRequest() {
    }

     public CreateLobbyRequest(Integer expiryInHours, Integer lobbySize) {
        this.expiryInHours = expiryInHours;
        this.lobbySize = lobbySize;
    }

    public Integer getExpiryInHours() {
        return expiryInHours;
    }

    public void setExpiryInHours(Integer expiryInHours) {
        this.expiryInHours = expiryInHours;
    }

    public Integer getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(Integer lobbySize) {
        this.lobbySize = lobbySize;
    }
}
