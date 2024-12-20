package com.rental.management.Customers;

public interface LoyaltyPoints {

    boolean isEligibleForReward();

    void redeemLoyaltyPoints();

    int getLoyaltyPoints();

}
