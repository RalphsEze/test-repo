/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.exception.UnauthorizedUserException
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

/**
 * Exception thrown when an unauthorized user attempts to access a protected resource.
 */
public class UnauthorizedUserException extends RuntimeException {

    /**
     * Constructs a new UnauthorizedUserException with a default unauthorized message.
     */
    public UnauthorizedUserException() {
        super("Unauthorized User");
    }
}
