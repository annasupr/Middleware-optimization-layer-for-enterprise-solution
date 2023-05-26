package com.upk.diploma.catalogservice.exception;

public class DataNotFoundException extends RuntimeException {
        public static final String ENTITY_BY_ID_NOT_FOUND_MESSAGE_TEMPLATE = "%s with ID %s does not exist or expired. " +
                "Try to use another ID.";

        public DataNotFoundException(String entity, Long id) {
                super(String.format(ENTITY_BY_ID_NOT_FOUND_MESSAGE_TEMPLATE, entity, id));
        }

        public DataNotFoundException(String entity, String email) {
                super(String.format(ENTITY_BY_ID_NOT_FOUND_MESSAGE_TEMPLATE, entity, email));
        }

        public DataNotFoundException(String message) {
                super(message);
        }
}
