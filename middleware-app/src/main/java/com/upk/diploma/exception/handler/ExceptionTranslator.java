package com.upk.diploma.exception.handler;

import com.upk.diploma.exception.RegisterUserException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.TimeoutException;

import static com.upk.diploma.service.customer.impl.UserAccountServiceImpl.*;

@ControllerAdvice
public class ExceptionTranslator {

    private String message;
    private String description;

    public static final String AUTH_ERROR = "Username or password is wrong";

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO handleAuthException(AuthException exc){
        if (exc.getMessage().contains(AUTH_ERROR)) {
            this.description = "Could not complete authentication";
        }
        return processFieldErrors(exc, this.message, this.description);
    }

//    @ExceptionHandler(ConnectException.class)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ResponseBody
//    public ErrorDTO handleConnectException(ConnectException exc){
//        if (exc.getMessage().contains("Connection refused: connect")) {
//            this.description = "Backend systems are unavailable currently, Please, try again later.";
//        }
//        return processFieldErrors(exc, this.message, this.description);
//    }

    @ExceptionHandler({TimeoutException.class})
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ErrorDTO handleTimeoutException(TimeoutException exc) {
        this.description = "Operation has reached out of the configured time";
        return processFieldErrors(exc, this.message, this.description);
    }

    @ExceptionHandler(RegisterUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO handleRegisterException(RegisterUserException exc){
        if (exc.getMessage().contains(ERROR_EMAIL)
                || exc.getMessage().contains(ERROR_USERNAME)
                || exc.getMessage().contains(REGISTRATION_NOT_EMPTY_BODY)
                || exc.getMessage().contains(REGISTRATION_WRONG_BODY)) {
            this.message = exc.getMessage();
            this.description = "Registration is unavailable.";
        }
        return processFieldErrors(exc, this.message, this.description);
    }

    private ErrorDTO processFieldErrors(final Exception ex,
                                        final String error,
                                        final String description
    ) {
        ErrorDTO errorDTO = new ErrorDTO(error, description);
        errorDTO.add(ex.getClass().getName(), ex.getMessage());
        return errorDTO;
    }
}
