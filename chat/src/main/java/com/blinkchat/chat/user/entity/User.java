package com.blinkchat.chat.user.entity;

import com.blinkchat.chat.lobby.entity.Lobby;
import com.blinkchat.chat.message.entity.Message;
import com.blinkchat.chat.user.repository.UserRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     @Column(unique = true , nullable=false)
    private String anonymousName;
     @Column(unique = true, nullable=false)
    private String sessionToken;
    @ManyToOne
    private Lobby lobby;
    private LocalDateTime joinedAt;
    private LocalDateTime lastSeenAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Message> messages;


    private static final String NUMBERS = "0123456789";
    public User(UserRepository userRepository) {
            anonymousName = generateRandomUsername();
    }

    public User() {
        anonymousName = generateRandomUsername();
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

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnonymousName() {
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }



    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(LocalDateTime lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }
}
