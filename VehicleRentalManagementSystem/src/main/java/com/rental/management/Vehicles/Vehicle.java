package com.rental.management.Vehicles;

import com.rental.management.Customers.RatingSystem;

public abstract class Vehicle {

    // Private encapsulated fields
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;
    private RatingSystem ratingSystem;

    // Constructors with validation

    public Vehicle(String vehicleId, String model, double baseRentalRate, boolean isAvailable) {
        setVehicleId(vehicleId);
        setModel(model);
        setBaseRentalRate(baseRentalRate);
        setIsAvailable(isAvailable);
        this.ratingSystem = new RatingSystem();

    }
    // Getters and setters


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        if(vehicleId == null || vehicleId.trim().isEmpty())
            throw new IllegalArgumentException("Vehicle id cannot be empty or null");
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model == null || model.trim().isEmpty()){
            throw new IllegalArgumentException("Vehicle model cannot be empty or null");
        }
        this.model = model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if(baseRentalRate < 0){
            throw new IllegalArgumentException("Base Rental Rate cannot be negative");
        }
        this.baseRentalRate = baseRentalRate;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void rateVehicle(int rating) {
        ratingSystem.rateVehicle(rating);
    }

    public double getAverageVehicleRating() {
        return ratingSystem.getAverageVehicleRating();
    }

    // Abstract methods for rental calculation
//    To calculate the rental cost
    public abstract double calculateRentalCost(int days);

//    to know its availability
    public abstract boolean isAvailableForRental();

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + getVehicleId() +
                ", model='" + getModel() +
                ", baseRentalRate=" + getBaseRentalRate() +
                ", isAvailable=" + getIsAvailable() +
                '}';
    }

    // To override equals to compare vehicles by their ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return vehicleId.equals(vehicle.vehicleId);
    }

    // To override hashCode for proper hash-based collection usage
    @Override
    public int hashCode() {
        return vehicleId.hashCode();
    }
}
