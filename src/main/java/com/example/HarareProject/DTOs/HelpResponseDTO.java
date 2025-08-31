package com.example.HarareProject.DTOs;

public class HelpResponseDTO {
    private Long userId;
    private Long helpRequestId;
    private String responseMessage;

    public Long getHelpRequestId() {
        return helpRequestId;
    }

    public void setHelpRequestId(Long helpRequestId) {
        this.helpRequestId = helpRequestId;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
