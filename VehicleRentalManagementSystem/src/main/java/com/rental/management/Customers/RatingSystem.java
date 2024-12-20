package com.rental.management.Customers;

import com.rental.management.Exceptions.InvalidRatingException;

import java.util.ArrayList;
import java.util.List;

    public class RatingSystem {

        // Store ratings for vehicles from customers
        private List<Integer> vehicleRatings;

        public RatingSystem() {
            this.vehicleRatings = new ArrayList<>();
        }

        // Add rating for a vehicle
        public void rateVehicle(int rating) throws InvalidRatingException {
            if (rating < 1 || rating > 5) {
                throw new InvalidRatingException("Error: Rating must be between 1 and 5.");
            }
            vehicleRatings.add(rating);
        }

        // Calculate average rating for vehicles
        public double getAverageVehicleRating() {
            if (vehicleRatings.isEmpty()) {
                return 0.0;
            }
            return vehicleRatings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
    }

