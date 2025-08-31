package com.example.HarareProject.Entity;

import java.time.LocalDateTime;

public class ErrorReponse {
    private LocalDateTime timestamp;

    private String message;

    private String details;

    public ErrorReponse(String details, String message, LocalDateTime timestamp) {
        this.details = details;
        this.message = message;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
