package com.rental.management.Agency;

import com.rental.management.Customers.Customer;
import com.rental.management.Exceptions.CustomerNotEligibleException;
import com.rental.management.Exceptions.VehicleNotAvailableException;
import com.rental.management.Transactions.RentalTransaction;
import com.rental.management.Vehicles.Car;

public class RentalService {

    public static void main(String[] args) {

        // Creating customers
        Customer customer1 = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Customer customer2 = new Customer("C002", "Adwoa Twum", "adwoa@example.com", "233389456493");

        // Creating vehicles
        Car car1 = new Car("V001", "SUV", 50.0, true, true, true, false, true);
        Car car2 = new Car("V002", "V8", 45.0, true, true, true, true, false);

        // Adding vehicles to the fleet
        RentalAgency.addVehicle(car1);
        RentalAgency.addVehicle(car2);

        // Rent a vehicle and handle error if possible
        try {
            RentalTransaction transaction1 = RentalAgency.rentVehicle(customer1, "V001", 5);
            // Checking if the customer is eligible for a reward
            if (customer1.isEligibleForReward()) {
                System.out.println("Customer " + customer1.getName() + " is eligible for a reward!");
                customer1.redeemLoyaltyPoints();
            } else {
                System.out.println("Customer " + customer1.getName() + " does not have enough loyalty points.");
            }
        } catch (VehicleNotAvailableException | CustomerNotEligibleException e) {
            System.out.println("Rental failed: " + e.getMessage());
        }

        // Rent another vehicle
        try {
            RentalTransaction transaction2 = RentalAgency.rentVehicle(customer2, "V002", 3);
            if (customer2.isEligibleForReward()) {
                System.out.println("Customer " + customer2.getName() + " is eligible for a reward!");
                customer2.redeemLoyaltyPoints();
            } else {
                System.out.println("Customer " + customer2.getName() + " does not have enough loyalty points.");
            }
        } catch (VehicleNotAvailableException | CustomerNotEligibleException e) {
            System.out.println("Rental failed: " + e.getMessage());
        }

        // Display active rentals
        RentalAgency.generateActiveRentalsReport();

        // Returning a vehicle
        RentalAgency.returnVehicle(customer1);

        // Display active rentals again
        RentalAgency.generateActiveRentalsReport();
    }
}

