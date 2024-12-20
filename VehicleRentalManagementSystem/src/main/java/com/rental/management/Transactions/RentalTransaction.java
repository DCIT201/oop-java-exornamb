package com.rental.management.Transactions;

import java.time.LocalDate;

import com.rental.management.Customers.Customer;
import com.rental.management.Vehicles.Vehicle;

public class RentalTransaction {
    // Fields
    private static int transactionCounter = 0; // To generate unique transaction IDs
    private int transactionId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double rentalCost;
    private LocalDate rentalDate;
    private boolean isReturned;

    // Constructor
    public RentalTransaction(Customer customer, Vehicle vehicle, int rentalDays, double rentalCost) {
        // Generate unique ID
        setCustomer(customer);
        setVehicle(vehicle);
        setRentalDays(rentalDays);
        setRentalCost(rentalCost);
        setRentalDate();// To set rental date to today
        setReturned(false);
        customer.setLoyaltyPoints(rentalDays * 2);

        this.transactionId = ++transactionCounter;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        this.vehicle = vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than zero.");
        }
        this.rentalDays = rentalDays;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        if (rentalCost < 0) {
            throw new IllegalArgumentException("Rental cost cannot be negative.");
        }
        this.rentalCost = rentalCost;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate() {
        this.rentalDate = LocalDate.now();
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    // Overridden Methods
    @Override
    public String toString() {
        return "RentalTransaction{" +
                "transactionId=" + getTransactionId() +
                ", customer=" + getCustomer().getName() +
                ", vehicle=" + getVehicle().getModel() +
                ", rentalDays=" + getRentalDays() +
                ", rentalCost=$" + getRentalCost() +
                ", rentalDate=" + getRentalDate() +
                ", isReturned=" + isReturned() +
                '}';
    }
}

