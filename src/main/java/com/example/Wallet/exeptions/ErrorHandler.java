package com.example.Wallet.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Обработчик исключений для MethodArgumentNotValidException.
     *
     * @param e объект MethodArgumentNotValidException
     * @return объект ErrorResponse с информацией об ошибке
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(final MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // 400
        ErrorResponse errorResponse = new ErrorResponse();
        log.error("{} {}", httpStatus.value(), e.getMessage());
        errorResponse.setStatus(httpStatus.name());
        errorResponse.setReason(httpStatus.getReasonPhrase());
        errorResponse.setMessage("Not enough funds to complete the operation");
        return errorResponse;
    }

    /**
     * Обработчик исключений для UncorrectedParametersException
     *
     * @param e объект Exception
     * @return объект ApiError с информацией об ошибке
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(UncorrectedParametersException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // 400
        ErrorResponse errorResponse = new ErrorResponse();
        log.debug("{} {}", httpStatus.value(), e.getMessage(), e);
        errorResponse.setStatus(httpStatus.name());
        errorResponse.setReason(httpStatus.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    /**
     * Обработчик исключений для NotFoundException.
     *
     * @param e объект NotFoundException
     * @return объект ErrorResponse с информацией об ошибке
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(final NotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND; // 404
        ErrorResponse errorResponse = new ErrorResponse();
        log.debug("{} {}", httpStatus.value(), e.getMessage(), e);
        errorResponse.setStatus(httpStatus.name());
        errorResponse.setReason(httpStatus.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
