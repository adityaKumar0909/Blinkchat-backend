package com.blinkchat.chat.exception;

public class MaxCapacityOfLobbyReachedException extends RuntimeException {
    public MaxCapacityOfLobbyReachedException(String message) {
        super(message);
    }
}
