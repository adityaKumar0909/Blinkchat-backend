package com.blinkchat.chat.lobby.controller;

import com.blinkchat.chat.exception.ErrorResponseDto;
import com.blinkchat.chat.lobby.dto.CreateLobbyRequest;
import com.blinkchat.chat.lobby.dto.CreateLobbyResponse;
import com.blinkchat.chat.lobby.dto.JoinLobbyRequest;
import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;
import com.blinkchat.chat.lobby.service.LobbyService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CreateLobbyResponse> createLobby(@Valid  @RequestBody CreateLobbyRequest body) {
        Integer expiryInMins = body.getExpiryPreset().getMinutes();
        Integer lobbySize = body.getLobbySize();
        CreateLobbyResponse createLobbyResponse = lobbyService.createLobby(expiryInMins, lobbySize);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createLobbyResponse);

    }

    @PostMapping("/join")
    public ResponseEntity<JoinLobbyResponse> joinLobby(@Valid @RequestBody JoinLobbyRequest body) {
        System.out.println("Recieved Request to join lobby");
        if (body.getLobbyId() == null || body.getLobbyId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        JoinLobbyResponse joinLobbyResponse = lobbyService.joinLobby(body);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinLobbyResponse);

    }


}
