package com.blinkchat.chat.enums;

public enum LobbyExpiryPreset {
    THIRTY_MINUTES(30),
    FORTY_FIVE_MINUTES(45),
    ONE_HOUR(60),
    THREE_HOURS(180),
    SIX_HOURS(360),
    TWELVE_HOURS(720);

    private final int minutes;

    LobbyExpiryPreset(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
