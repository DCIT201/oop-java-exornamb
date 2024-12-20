package com.rental.management.Exceptions;

public class LoyaltyPointsNotSufficientException extends RuntimeException {
    public LoyaltyPointsNotSufficientException(String message) {
        super(message);
    }
}
