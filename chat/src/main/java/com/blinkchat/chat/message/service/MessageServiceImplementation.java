package com.blinkchat.chat.message.service;

import com.blinkchat.chat.lobby.entity.Lobby;
import com.blinkchat.chat.lobby.repository.LobbyRepository;
import com.blinkchat.chat.message.dto.SendMessageRequest;
import com.blinkchat.chat.message.dto.SendMessageResponse;
import com.blinkchat.chat.message.entity.Message;
import com.blinkchat.chat.message.repository.MessageRepository;
import com.blinkchat.chat.user.entity.User;
import com.blinkchat.chat.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImplementation implements MessageService{
    private final LobbyRepository lobbyRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImplementation(LobbyRepository lobbyRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.lobbyRepository = lobbyRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public SendMessageResponse sendMessage(SendMessageRequest body, String sessionToken) {
        Message message = new Message();
        if(body.getContent() == null || body.getContent().isEmpty()){
            return null;
        }
        message.setContent(body.getContent());
        message.setCreatedAt(java.time.LocalDateTime.now());
        User user = userRepository.findUserBySessionToken(sessionToken);
        if(user == null){
            return null;
        }
        Lobby lobby = user.getLobby();
        message.setUser(user);
        message.setLobby(lobby);
        messageRepository.save(message);
        return new SendMessageResponse(message.getMessageId(),message.getCreatedAt());

    }
}
