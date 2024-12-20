package com.rental.management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rental.management.Exceptions.LoyaltyPointsNotSufficientException;
import com.rental.management.Customers.Customer;

public class CustomerTests {

    @Test
    public void testCustomerConstructor() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        assertEquals("C001", customer.getCustomerId());
        assertEquals("Jennifer Banibensu", customer.getName());
        assertEquals("jennifer@example.com", customer.getEmail());
    }

    @Test
    public void testRedeemLoyaltyPointsNotSufficient() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        assertThrows(LoyaltyPointsNotSufficientException.class, customer::redeemLoyaltyPoints);
    }

    @Test
    public void testRedeemLoyaltyPointsSuccessful() throws LoyaltyPointsNotSufficientException {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        customer.setLoyaltyPoints(120);
        customer.redeemLoyaltyPoints();
        assertEquals(20, customer.getLoyaltyPoints());
    }
}
