/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.exception.InvalidParamsException
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

/**
 * Exception thrown when one or more request parameters are invalid or malformed.
 */
public class InvalidParamsException extends RuntimeException {

    /**
     * Constructs a new InvalidParamsException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public InvalidParamsException(String message) {
        super(message);
    }
}
