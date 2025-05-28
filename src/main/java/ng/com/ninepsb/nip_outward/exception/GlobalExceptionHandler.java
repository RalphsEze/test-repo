/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.config.GlobalExceptionHandler
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

import ng.com.ninepsb.logger_lib.service.CustomLogger;
import ng.com.ninepsb.nip_outward.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static ng.com.ninepsb.nip_outward.dto.response.ApiResponse.*;


/**
 * Global exception handler for the Virtual Account Service.
 * Provides centralized exception handling for various application-specific and validation errors.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final CustomLogger log;

    public GlobalExceptionHandler(CustomLogger log) {
        this.log = log;
    }

    /**
     * Handles {@link ResourceNotFoundException} and returns a NOT_FOUND (404) response.
     *
     * @param e the thrown exception
     * @return standardized error response with message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<String> handleNotFoundException(RuntimeException e) {
        return new ApiResponse<>(STATUS_CODE_NOT_FOUND, e.getMessage());
    }

    /**
     * Handles {@link Exception} and returns a NOT_FOUND (404) response.
     *
     * @param e the thrown exception
     * @return standardized error response with message
     */
    @ExceptionHandler({
            IllegalStateException.class,
            InternalServiceException.class,
            Exception.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> handleNotFoundException(Exception e) {
        log.logError(e.getMessage());
        return new ApiResponse<>(STATUS_CODE_INTERNAL_SERVER_ERROR, "Something went wrong. Try again later.");
    }

    /**
     * Handles {@link RangeExhaustedException} and {@link InvalidParamsException} with a BAD_REQUEST (400) response.
     *
     * @param e the thrown exception
     * @return standardized error response with message
     */
    @ExceptionHandler({RangeExhaustedException.class, InvalidParamsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleBadRequest(RuntimeException e) {
        return new ApiResponse<>(STATUS_CODE_BAD_REQUEST, e.getMessage());
    }

    /**
     * Handles {@link UnauthorizedUserException} with an UNAUTHORIZED (401) response.
     *
     * @param e the thrown exception
     * @return standardized error response with message
     */
    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<String> handleUnauthorized(RuntimeException e) {
        return new ApiResponse<>(STATUS_CODE_UNAUTHORIZED, e.getMessage());
    }

    /**
     * Handles {@link ForbiddenUserException} with an FORBIDDEN (403) response.
     *
     * @param e the thrown exception
     * @return standardized error response with message
     */
    @ExceptionHandler(ForbiddenUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<String> handleForbidden(RuntimeException e) {
        return new ApiResponse<>(STATUS_CODE_FORBIDDEN, e.getMessage());
    }

    /**
     * Handles {@link MethodArgumentNotValidException} for invalid request body validation.
     *
     * @param exception the thrown exception
     * @return standardized error response with first validation error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ApiResponse<>(
                STATUS_CODE_BAD_REQUEST,
                exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }


    /**
     * Handles {@link HttpRequestMethodNotSupportedException} for invalid request body validation.
     *
     * @param exception the thrown exception
     * @return standardized error response with first validation error message
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleRequestMethodException(HttpRequestMethodNotSupportedException exception) {
        return new ApiResponse<>(
                STATUS_CODE_BAD_REQUEST,
                "%s method is not supported.".formatted(exception.getMethod())
        );
    }


}
