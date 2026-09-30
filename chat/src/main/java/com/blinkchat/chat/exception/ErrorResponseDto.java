package com.blinkchat.chat.exception;

import java.util.Map;

public class ErrorResponseDto {

    private String errorMessage;
    private Map<String,String> errorDetails;


    public ErrorResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public ErrorResponseDto(String errorMessage, Map<String,String> errorDetails) {
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
