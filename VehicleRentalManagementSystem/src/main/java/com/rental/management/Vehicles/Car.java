package com.rental.management.Vehicles;

import com.rental.management.Customers.Customer;

public class Car extends Vehicle implements Rentable {

    private boolean hasGPS;
    private boolean hasAirConditioner;
    private boolean hasChildSeat;

    public Car(String vehicleId, String model, double baseRentalRate, boolean isAvailable, boolean hasGPS, boolean hasAirConditioner, boolean hasChildSeat, boolean hasPremiumInsurance) {
        super(vehicleId, model, baseRentalRate, isAvailable);
        setHasGPS(hasGPS);
        setHasAirConditioner(hasAirConditioner);
        setHasChildSeat(hasChildSeat);
    }

    @Override
    public double calculateRentalCost(int days) {
        double total = getBaseRentalRate() * days;
        if (isHasChildSeat()) {
            total *= 3;
        }
        if (isHasAirConditioner()) {
            total *= 2;
        }
        if (isHasGPS()) {
            total += 40;
        }

        return total;
    }

    @Override
    public boolean isAvailableForRental() {

        return getIsAvailable();
    }

    public boolean isHasGPS() {
        return hasGPS;
    }

    public void setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public void setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }

    public boolean isHasChildSeat() {
        return hasChildSeat;
    }

    public void setHasChildSeat(boolean hasChildSeat) {
        this.hasChildSeat = hasChildSeat;
    }

    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            System.out.println("Car has been rented to " + customer.getName() + " for " + days + " days.");
            setIsAvailable(false);
        } else {
            System.out.println("Car is not available for rent.");
        }

    }

    @Override
    public void returnVehicle() {
        System.out.println("Car has successfully returned. Happy renting!");
        setIsAvailable(true);

    }
}
