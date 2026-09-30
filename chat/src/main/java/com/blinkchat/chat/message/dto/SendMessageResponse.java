package com.blinkchat.chat.message.dto;

import java.time.LocalDateTime;

public class SendMessageResponse {
    private Long messageId;
    private LocalDateTime sentAt;


    public SendMessageResponse(Long messageId, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.sentAt = sentAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
