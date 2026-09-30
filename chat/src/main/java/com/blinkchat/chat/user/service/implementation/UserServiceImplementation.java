package com.blinkchat.chat.user.service.implementation;

import com.blinkchat.chat.lobby.dto.JoinLobbyResponse;
import com.blinkchat.chat.lobby.entity.Lobby;
import com.blinkchat.chat.user.entity.User;
import com.blinkchat.chat.user.repository.UserRepository;
import com.blinkchat.chat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;


@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    private static final String NUMBERS = "0123456789";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();


    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  String generateRandomUsername(){
        StringBuilder username = new StringBuilder();
        Random random = new Random();
        List<String> adjectives = List.of("Sneaky","Sleepy","Chaotic","Tiny","Dizzy","Grumpy","Hyper",
                "Lazy","Wobbly","Nerdy","Fluffy","Clumsy","Curious","Goofy","Hungry","Chill","Bouncy");
        List<String> animals = List.of("Panda","Sloth","Otter","Koala","Hedgehog","Penguin","Raccoon",
                "Capybara","Goose","Wolf","Monkey","Owl","Llama","Turtle","Hamster","Crow");
        username.append(adjectives.get(random.nextInt(adjectives.size())));
        username.append(animals.get(random.nextInt(animals.size())));
        for(int i = 0; i < 3; i++){
            username.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        return  username.toString();
    }

    private String generateSessionToken() {
        byte[] randomBytes = new byte[32];
        SECURE_RANDOM.nextBytes(randomBytes);
        return Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(randomBytes);
    }

    @Override
    public JoinLobbyResponse createUser(Lobby lobby) {
        User user = new User();
        String uniqueAnonymousName;
        do{
            uniqueAnonymousName = generateRandomUsername();
        }while(userRepository.existsByAnonymousName(uniqueAnonymousName));
        user.setAnonymousName(uniqueAnonymousName);
        user.setSessionToken(generateSessionToken());
        user.setJoinedAt(java.time.LocalDateTime.now());
        user.setLobby(lobby);
        userRepository.save(user);
        return new JoinLobbyResponse(user.getAnonymousName(), user.getLobby().getLobbyId(),user.getSessionToken());
    }
}
