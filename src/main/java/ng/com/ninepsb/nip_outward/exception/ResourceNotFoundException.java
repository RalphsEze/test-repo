/*
 * -----------------------------------------------------------------------------------
 * Copyright (c) 2024 9 Payment Service Bank
 * -----------------------------------------------------------------------------------
 * This code is the property of 9 Payment Service Bank. Unauthorized copying,
 * sharing, or use of this code, via any medium, is strictly prohibited
 * without express permission from 9 Payment Service Bank.
 * -----------------------------------------------------------------------------------
 * @package    ng.com.ninepsb.virtual_account_service.exception.ResourceNotFoundException
 * @author     Raphael Eze
 * @license    Proprietary
 * @version    1.0.0
 * @link       https://www.9psb.com.ng
 */

package ng.com.ninepsb.nip_outward.exception;

/**
 * Exception thrown when a requested resource is not found in the system.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason the resource was not found
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
