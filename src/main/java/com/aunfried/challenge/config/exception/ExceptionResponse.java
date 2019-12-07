package com.aunfried.challenge.config.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.FieldError;

public class ExceptionResponse {

    private ErrorCode errorCode;
    private String message;
    private final List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}

