package com.rental.management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rental.management.Exceptions.InvalidRatingException;
import com.rental.management.Customers.RatingSystem;


public class RatingSystemTests {

    @Test
    public void testRateVehicleValidRating() throws InvalidRatingException {
        RatingSystem ratingSystem = new RatingSystem();
        ratingSystem.rateVehicle(3);
        assertEquals(3.0, ratingSystem.getAverageVehicleRating(), 0.01);
    }

    @Test
    public void testGetAverageRatingNoRatings() {
        RatingSystem ratingSystem = new RatingSystem();
        assertEquals(0.0, ratingSystem.getAverageVehicleRating(), 0.01);
    }
}
