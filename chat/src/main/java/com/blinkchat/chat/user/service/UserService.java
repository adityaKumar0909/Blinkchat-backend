package com.blinkchat.chat.user.service;

import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;
import com.blinkchat.chat.lobby.entity.Lobby;

public interface UserService {
    JoinLobbyResponse createUser(Lobby lobby);
}
