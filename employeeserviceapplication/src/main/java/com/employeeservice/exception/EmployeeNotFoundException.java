/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.employeeservice.exception;

/**
 * Custom exception class for handling unauthorized or null values
 *
 * 
 */
public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    public EmployeeNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
