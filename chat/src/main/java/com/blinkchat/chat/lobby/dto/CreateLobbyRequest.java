package com.blinkchat.chat.lobby.dto;

import com.blinkchat.chat.enums.LobbyExpiryPreset;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

public class CreateLobbyRequest {
    @NotNull(message = "ExpiryPreset cannot be null")
    private LobbyExpiryPreset expiryPreset;

    @Min(value = 2, message = "Lobby size must be at least 2")
    @Max(value = 20, message = "Lobby size must be at most 20")
    @NotNull(message = "Lobby size cannot be null")
    private Integer lobbySize = 20;

     public CreateLobbyRequest() {
    }

     public CreateLobbyRequest(LobbyExpiryPreset expiryPreset, Integer lobbySize) {
        this.expiryPreset = expiryPreset;
        this.lobbySize = lobbySize;
    }

    public LobbyExpiryPreset getExpiryPreset() {
        return expiryPreset;
    }

    public void setExpiryPreset(LobbyExpiryPreset expiryPreset) {
        this.expiryPreset = expiryPreset;
    }

    public Integer getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(Integer lobbySize) {
        this.lobbySize = lobbySize;
    }
}
