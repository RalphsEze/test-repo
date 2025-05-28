/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.exception.RangeExhaustedException
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

/**
 * Exception thrown when the virtual account number range has been exhausted.
 */
public class RangeExhaustedException extends RuntimeException {

    /**
     * Constructs a new RangeExhaustedException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public RangeExhaustedException(String message) {
        super(message);
    }
}
