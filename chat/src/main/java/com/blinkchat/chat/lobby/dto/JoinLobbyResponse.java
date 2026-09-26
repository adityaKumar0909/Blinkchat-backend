package com.blinkchat.chat.lobby.dto;

public class JoinLobbyResponse {
     private String anonymousName;
    private String lobbyId;
    private String sessionToken;

    public JoinLobbyResponse(String anonymousName, String lobbyId, String sessionToken) {
        this.anonymousName = anonymousName;
        this.lobbyId = lobbyId;
        this.sessionToken = sessionToken;
    }

    public String getAnonymousName() {
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

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
