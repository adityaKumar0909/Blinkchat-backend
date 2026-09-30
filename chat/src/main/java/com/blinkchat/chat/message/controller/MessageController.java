package com.blinkchat.chat.message.controller;

import com.blinkchat.chat.exception.RateLimitExceededException;
import com.blinkchat.chat.message.dto.SendMessageRequest;
import com.blinkchat.chat.message.dto.SendMessageResponse;
import com.blinkchat.chat.message.service.MessageService;
import com.blinkchat.chat.ratelimit.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    RateLimiterService rateLimiterService;
    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService, RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestHeader("Authorization") String authorization,
                                                           @RequestBody SendMessageRequest body) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        String sessionToken = authorization.substring(7);
        if(sessionToken.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        if(!rateLimiterService.isAllowed(sessionToken)){
            throw new RateLimitExceededException("Rate limit exceeded. Please try again later.");
        }

        SendMessageResponse messageResponse = messageService.sendMessage(body, sessionToken);
        if (messageResponse == null) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(200).body(messageResponse);
    }
}
