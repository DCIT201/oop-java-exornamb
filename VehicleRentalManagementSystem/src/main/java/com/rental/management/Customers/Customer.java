package com.rental.management.Customers;
import com.rental.management.Exceptions.CustomerNotEligibleException;
import com.rental.management.Exceptions.LoyaltyPointsNotSufficientException;
import com.rental.management.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Customer implements LoyaltyPoints {

    private String customerId;
    private String name;
    private String email;
    private String phone;
    private boolean isEligible;
    private List<Vehicle> rentalHistory = new ArrayList<>(); // To list of all rented vehicles
    private Vehicle currentRental;
    private int loyaltyPoints;

    public Customer(String customerId, String name, String email, String phone) {
            setCustomerId(customerId);
            setName(name);
            setEmail(email);
            setPhone(phone);
            setEligible(true);
            setLoyaltyPoints(0);

    }

//    Getters and setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if(customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer id cannot be empty or null");
        }
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty or null");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Customer email cannot be null or does not contain @");
        }

        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone == null || !phone.matches("\\d{12}")) {
            throw new IllegalArgumentException("Customer phone number cannot be null or matches the 233 000 000 000");
        }
        this.phone = phone;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public List<Vehicle> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(Vehicle vehicle) {

        rentalHistory.add(vehicle);
    }

    public Vehicle getCurrentRental() {
        return currentRental;
    }

    public void setCurrentRental(Vehicle currentRental) {
        this.currentRental = currentRental;
    }

    // Getters and setters
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints += loyaltyPoints;
    }

    @Override
    public boolean isEligibleForReward() {
        return loyaltyPoints >= 100; // Example: eligible for reward if points are 100 or more
    }


//    To redeem their loyalty points
    @Override
    public void redeemLoyaltyPoints() throws LoyaltyPointsNotSufficientException {
        if (isEligibleForReward()) {
            System.out.println("Redeeming loyalty points for a reward...");
            loyaltyPoints -= 100; // Example: redeem 100 points for a reward
        } else {
           throw new LoyaltyPointsNotSufficientException("Not enough loyalty points to redeem.");
        }
    }

//    Manage customer rental history

//    To check the eligibility of the customer
    public void checkEligibility() {
        setEligible(currentRental != null);
    }

//   Track current rentals
    public void rentVehicle(Vehicle vehicle) throws CustomerNotEligibleException {
        if(isEligible()){
            setCurrentRental(vehicle);
            setEligible(false);
            vehicle.setIsAvailable(false);
            setLoyaltyPoints(5);
        }else{
            throw new CustomerNotEligibleException("Customer is not eligible");

        }
    }

//    To return the vehicle
    public void returnVehicle(Vehicle vehicle) {

        if (currentRental == null) {
            throw new IllegalStateException("Customer has no active rental to return.");
        }
        setCurrentRental(null);
        setEligible(true);
        vehicle.setIsAvailable(true);
        setRentalHistory(vehicle);
        setLoyaltyPoints(4);

    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + getCustomerId() +
                ", name='" + getName() +
                ", email='" + getEmail() +
                ", phone='" + getPhone() +
                ", isEligible=" + isEligible() +
                ", rentalHistory=" + rentalHistory.size() +
                ", currentRental=" + (currentRental != null ? currentRental.getModel() : "None") +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }

}
