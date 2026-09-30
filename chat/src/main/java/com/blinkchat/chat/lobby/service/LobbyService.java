package com.blinkchat.chat.lobby.service;

import com.blinkchat.chat.lobby.dto.CreateLobbyResponse;
import com.blinkchat.chat.lobby.dto.JoinLobbyRequest;
import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;

public interface LobbyService {

    CreateLobbyResponse createLobby(Integer expiryInMins, Integer lobbySize);
    JoinLobbyResponse joinLobby(JoinLobbyRequest body);

}
