package com.rental.management;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rental.management.Vehicles.Car;

public class CarTests {

    @Test
    public void testCalculateRentalCost() {
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        double rentalCost = car.calculateRentalCost(5);
        assertEquals(510.0, rentalCost, 0.01); // Base rate (50 * 5) + GPS + A/C + Child Seat = 250 + 40 + 20 + 40
    }

    @Test
    public void testIsAvailableForRental() {
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        assertTrue(car.isAvailableForRental());
    }
}
