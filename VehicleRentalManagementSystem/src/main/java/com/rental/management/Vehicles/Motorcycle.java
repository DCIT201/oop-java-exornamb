package com.rental.management.Vehicles;

import com.rental.management.Customers.Customer;

public class Motorcycle extends Vehicle implements Rentable{

    private boolean hasHelmet;
    private boolean hasSaddlebags;
    private boolean hasRoadsideAssistance;

    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean isAvailable, boolean hasHelmet, boolean hasSaddlebags, boolean hasRoadsideAssistance) {
        super(vehicleId, model, baseRentalRate, isAvailable);
        setHasHelmet(hasHelmet);
        setHasSaddlebags(hasSaddlebags);
        setHasRoadsideAssistance(hasRoadsideAssistance);
    }


//    The getters and setters
    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public boolean isHasSaddlebags() {
        return hasSaddlebags;
    }

    public void setHasSaddlebags(boolean hasSaddlebags) {
        this.hasSaddlebags = hasSaddlebags;
    }

    public boolean isHasRoadsideAssistance() {
        return hasRoadsideAssistance;
    }

    public void setHasRoadsideAssistance(boolean hasRoadsideAssistance) {
        this.hasRoadsideAssistance = hasRoadsideAssistance;
    }

    @Override
    public double calculateRentalCost(int days) {
        double total = getBaseRentalRate() * days;
        if(isHasRoadsideAssistance()) {
            total *= 3;
        }
        if(isHasSaddlebags()){
            total *= 2;
        }
        if(isHasHelmet()) {
            total += 40;
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
            System.out.println("Motorcycle has been rented to " + customer.getName() + " for " + days + " days.");
            setIsAvailable(false);
        } else {
            System.out.println("Motorcycle is not available for rent.");
        }

    }

    @Override
    public void returnVehicle() {
        System.out.println("Motorcycle has successfully returned. Happy renting!");
        setIsAvailable(true);

    }
}
