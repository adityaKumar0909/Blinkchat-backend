package com.blinkchat.chat.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SendMessageRequest {
    @Size(max=500)
    @NotBlank(message = "Message cannot be blank")
    private String content;

    public SendMessageRequest(String content) {
        this.content = content;
    }
    public SendMessageRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
