package com.blinkchat.chat.lobby.service.implementation;

import com.blinkchat.chat.exception.MaxCapacityOfLobbyReachedException;
import com.blinkchat.chat.exception.ResourceNotFoundException;
import com.blinkchat.chat.lobby.dto.CreateLobbyResponse;
import com.blinkchat.chat.lobby.dto.JoinLobbyRequest;
import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;
import com.blinkchat.chat.lobby.entity.Lobby;
import com.blinkchat.chat.lobby.repository.LobbyRepository;
import com.blinkchat.chat.lobby.service.LobbyService;
import com.blinkchat.chat.user.entity.User;
import com.blinkchat.chat.user.repository.UserRepository;
import com.blinkchat.chat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class LobbyServiceImplementation implements LobbyService {

    private final LobbyRepository lobbyRepository;
    private final UserRepository userRepository;
    private final UserService userService ;

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateRandomLobbyId() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            //chooses a random index in the CHARACTER string and then appends that character
            //to a stringbuilder
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    @Autowired
    public LobbyServiceImplementation(LobbyRepository lobbyRepository, UserService userService, UserRepository userRepository) {
        this.lobbyRepository = lobbyRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public CreateLobbyResponse createLobby(Integer expiryInMins, Integer lobbySize) {
        Lobby lobby = new Lobby();
        lobby.setCreatedAt(LocalDateTime.now());
        lobby.setExpiresAt(LocalDateTime.now().plusMinutes(expiryInMins));
        lobby.setLobbySize(lobbySize);
        String uniqueLobbyId;
        do{
            uniqueLobbyId = generateRandomLobbyId();
        }while(lobbyRepository.existsByLobbyId(uniqueLobbyId));
        lobby.setLobbyId(uniqueLobbyId);
        lobbyRepository.save(lobby);
        return new CreateLobbyResponse(lobby.getLobbyId(),lobby.getExpiresAt(),lobby.getLobbySize());
    }

    @Override
public JoinLobbyResponse joinLobby(JoinLobbyRequest body) {

    //Extract info from body
    String lobbyId = body.getLobbyId();
    String sessionToken = body.getSessionToken();
    //Find lobby by extracted info
    Lobby lobby = lobbyRepository.findByLobbyId(lobbyId);

    //Lobby not found
    if (lobby == null) {
        throw new ResourceNotFoundException("Lobby not found.");
    }
    //Sessiontoken not found/empty -> new user
    if (sessionToken == null || sessionToken.isEmpty()) {
        //If lobby is full send this response
        if (lobby.getCurrentUsers() >= lobby.getLobbySize()) {
            throw new MaxCapacityOfLobbyReachedException("Lobby is full.");
        }
        //Creat a new user
        lobby.setCurrentUsers(lobby.getCurrentUsers() + 1);
        lobbyRepository.save(lobby);
        return userService.createUser(lobby);

    }

    //Find user with sessionToken
    User user = userRepository.findUserBySessionToken(sessionToken);

    //User not found -> lobby deleted / User wants to join another lobby
    if (user == null || !user.getLobby().getLobbyId().equals(lobbyId)) {
        //If new lobby full send this response
       if (lobby.getCurrentUsers() >= lobby.getLobbySize()) {
            throw new MaxCapacityOfLobbyReachedException("Lobby is full.");
        }
       //Add user to lobby
        lobby.setCurrentUsers(lobby.getCurrentUsers() + 1);
         lobbyRepository.save(lobby);
         return userService.createUser(lobby);
    }
    //Same user rejoins the lobby simply add him to lobby
    return new JoinLobbyResponse(
            user.getAnonymousName(),
            user.getLobby().getLobbyId(),
            user.getSessionToken()
    );
}

}
