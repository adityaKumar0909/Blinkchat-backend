package com.blinkchat.chat.message.service;

import com.blinkchat.chat.message.dto.SendMessageRequest;
import com.blinkchat.chat.message.dto.SendMessageResponse;

public interface MessageService {
    SendMessageResponse sendMessage(SendMessageRequest body, String sessionToken);
}
