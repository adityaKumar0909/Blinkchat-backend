package com.blinkchat.chat.lobby.repository;

import com.blinkchat.chat.lobby.entity.Lobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbyRepository  extends CrudRepository<Lobby,Long> {

    boolean existsByLobbyId(String lobbyId);
    Lobby findByLobbyId(String lobbyId);

}
