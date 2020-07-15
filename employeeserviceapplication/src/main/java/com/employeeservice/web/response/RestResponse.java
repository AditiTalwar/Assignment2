
package com.employeeservice.web.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;


public class RestResponse<T> {

    @JsonView
    private final Status status;

    @JsonView
    private final LocalDateTime requestedDate = LocalDateTime.now();

    @JsonView
    private final String description;

    @JsonView
    private final String message;

    @JsonView
    private final T data;

    public enum Status {
                        SUCCESS,
                        FAILURE;
    }

    public RestResponse(Status status, String description, String message, T data) {
        this.status = status;
        this.description = description;
        this.message = message;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
