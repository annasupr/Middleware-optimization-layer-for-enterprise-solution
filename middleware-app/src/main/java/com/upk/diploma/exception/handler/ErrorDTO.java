package com.upk.diploma.exception.handler;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDTO implements Serializable {
    private final String message;
    private final String description;

    private List<FieldErrorDTO> fieldErrors;

    public ErrorDTO(String message,
                    String description
    ) {
        this.message = message;
        this.description = description;
    }

    public void add(String objectName,
                    String field,
                    String message
    ) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDTO(objectName, field, message));
    }

    public void add(String objectName,
                    String message
    ) {
        add(objectName, null, message);
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
