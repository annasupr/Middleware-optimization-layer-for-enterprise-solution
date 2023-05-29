package com.upk.diploma.exception.handler;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldErrorDTO implements Serializable {
    private final String objectName;

    private final String field;

    private final String message;

    public FieldErrorDTO(String dto,
                         String field,
                         String message
    ) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }
}
