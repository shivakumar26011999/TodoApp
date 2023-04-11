package com.todo.todo.exception;

public class NotFoundException extends RuntimeException{
    private int statusCode;
    private String description;

    public NotFoundException(int statusCode, String description) {
        super(description);
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
