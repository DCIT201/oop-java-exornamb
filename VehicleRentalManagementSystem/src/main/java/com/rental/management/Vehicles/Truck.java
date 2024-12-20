package com.rental.management.Vehicles;

import com.rental.management.Customers.Customer;

public class Truck extends Vehicle implements Rentable {

    private double loadCapacity;
    private boolean hasTowingPackage;

    public Truck(String vehicleId, String model, double baseRentalRate, boolean isAvailable, double loadCapacity, boolean hasTowingPackage) {
        super(vehicleId, model, baseRentalRate, isAvailable);
        setHasTowingPackage(hasTowingPackage);
        setLoadCapacity(loadCapacity);
    }


//    Getters and setters

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean isHasTowingPackage() {
        return hasTowingPackage;
    }

    public void setHasTowingPackage(boolean hasTowingPackage) {
        this.hasTowingPackage = hasTowingPackage;
    }

    @Override
    public double calculateRentalCost(int days) {
        double total = getBaseRentalRate() * days;
        if(getLoadCapacity() < 100){
            total *= 2;
        }else if(getLoadCapacity() >= 100 && getLoadCapacity() <= 500){
            total *= 3;
        }else{
            total *= 4;
        }
        if(isHasTowingPackage()){
            total += 50;
        }

        return total;
    }

    @Override
    public boolean isAvailableForRental() {
        return getIsAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            System.out.println("Truck has been rented to " + customer.getName() + " for " + days + " days.");
            setIsAvailable(false);
        } else {
            System.out.println("Truck is not available for rent.");
        }

    }

    @Override
    public void returnVehicle() {
        System.out.println("Truck has successfully returned. Happy renting!");
        setIsAvailable(true);

    }
}
