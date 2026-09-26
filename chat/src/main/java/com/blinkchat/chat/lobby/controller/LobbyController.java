package com.blinkchat.chat.lobby.controller;

import com.blinkchat.chat.lobby.dto.CreateLobbyRequest;
import com.blinkchat.chat.lobby.dto.CreateLobbyResponse;
import com.blinkchat.chat.lobby.dto.JoinLobbyRequest;
import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;
import com.blinkchat.chat.lobby.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobby")
public class LobbyController {

    LobbyService lobbyService;
    @Autowired
    LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateLobbyResponse> createLobby(@RequestBody CreateLobbyRequest body) {
        Integer expiryInHours = body.getExpiryInHours();
        Integer lobbySize = body.getLobbySize();
        CreateLobbyResponse createLobbyResponse = lobbyService.createLobby(expiryInHours, lobbySize);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createLobbyResponse);

    }

    @PostMapping("/join")
    public ResponseEntity<JoinLobbyResponse> joinLobby(@RequestBody JoinLobbyRequest body) {

        if (body.getLobbyId() == null || body.getLobbyId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        JoinLobbyResponse joinLobbyResponse = lobbyService.joinLobby(body);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinLobbyResponse);

    }


}
