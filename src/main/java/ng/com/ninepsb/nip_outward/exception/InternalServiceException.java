/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.exception
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

/**
 * Thrown to indicate that an unexpected internal error occurred while processing
 * a request. This is typically used when a more specific exception is not applicable.
 */
public class InternalServiceException extends RuntimeException {

    /**
     * Constructs a new {@code InternalServiceException} with a default error message.
     */
    public InternalServiceException() {
        super("Something went wrong. Try again later.");
    }
}
