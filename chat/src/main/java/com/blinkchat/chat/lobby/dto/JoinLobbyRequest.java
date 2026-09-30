package com.blinkchat.chat.lobby.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class JoinLobbyRequest {
    @NotBlank(message = "LobbyId cannot be blank")
    @Pattern(regexp ="^[A-Z0-9]{6}$", message = "LobbyId must be 6 alphanumeric characters" )
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
