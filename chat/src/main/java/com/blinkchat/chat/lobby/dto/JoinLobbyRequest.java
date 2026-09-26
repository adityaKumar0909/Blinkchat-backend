package com.blinkchat.chat.lobby.dto;

public class JoinLobbyRequest {
    private String lobbyId;
    private String sessionToken;

    public JoinLobbyRequest(String lobbyId, String sessionToken) {
        this.lobbyId = lobbyId;
        this.sessionToken = sessionToken;
    }

    public JoinLobbyRequest() {}

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
