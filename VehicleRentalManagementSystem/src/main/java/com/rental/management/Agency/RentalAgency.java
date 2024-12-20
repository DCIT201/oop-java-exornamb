package com.rental.management.Agency;

import com.rental.management.Customers.Customer;
import com.rental.management.Exceptions.CustomerNotEligibleException;
import com.rental.management.Exceptions.VehicleNotAvailableException;
import com.rental.management.Transactions.RentalTransaction;
import com.rental.management.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class RentalAgency {
    private static final String agencyName = "Jennifer's Rental Agency";
    private static List<Vehicle> vehicleFleet = new ArrayList<>() ; // To list of all vehicles in the fleet
    private static List<RentalTransaction> rentalTransactions = new ArrayList<>(); // To list of all rental transactions

    // To add a vehicle to the fleet
    public static void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        vehicleFleet.add(vehicle);
        System.out.println("Vehicle added: " + vehicle.getModel());
    }

    // To remove a vehicle from the fleet
    public static void removeVehicle(String vehicleId) {

        boolean removed = vehicleFleet.removeIf(vehicle -> vehicle.getVehicleId().equals(vehicleId));
        if(removed) {
            System.out.println("Vehicle removed: " + vehicleId);
        }else{
            System.out.println("Vehicle not found: " + vehicleId);
        }
    }

    // To get a list of available vehicles
    public static List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.isAvailableForRental()) {
                availableVehicles.add(vehicle);
            }
        }

        return availableVehicles;
    }

//    To get all vehicles
        public static List<Vehicle> getAllVehicles() {
            return vehicleFleet;
        }


        // To rent a vehicle
        public static RentalTransaction rentVehicle(Customer customer, String vehicleId, int days) throws CustomerNotEligibleException, VehicleNotAvailableException {
            Vehicle vehicle = findVehicleById(vehicleId);

            if (vehicle == null) {
                System.out.println("Vehicle not found!");
                return null;
            }

            if (!vehicle.isAvailableForRental()) {
                throw new VehicleNotAvailableException("Vehicle is not available for rental.");
            }

            if (!checkCustomerEligibility(customer)) {
                throw new CustomerNotEligibleException("Customer is not eligible for rental.");
            }

            // To create a rental transaction
            double rentalCost = vehicle.calculateRentalCost(days);
            RentalTransaction transaction = new RentalTransaction(customer, vehicle, days, rentalCost);
            rentalTransactions.add(transaction);

            // To update vehicle availability
            customer.rentVehicle(vehicle);
            vehicle.setIsAvailable(false);

            System.out.println("Vehicle rented successfully! Total cost: " + rentalCost + "Ghana cedis");
            return transaction;
        }


    public static void returnVehicle(Customer customer) {
        RentalTransaction transactionToReturn = null;

        // To find the active rental for the customer
        for (RentalTransaction transaction : rentalTransactions) {
            if (transaction.getCustomer().equals(customer) && !transaction.isReturned()) {
                transactionToReturn = transaction;
                break;
            }
        }

        if (transactionToReturn == null) {
            System.out.println("No active rental found for the customer.");
            return;
        }

        // To update the transaction and vehicle status
        customer.setLoyaltyPoints(5);
        transactionToReturn.setReturned(true);
        transactionToReturn.getVehicle().setIsAvailable(true);

        System.out.println("Vehicle returned successfully: " + transactionToReturn.getVehicle().getModel());
    }

    public static void generateActiveRentalsReport() {
        System.out.println("Active Rentals Report:");
        for (RentalTransaction transaction : rentalTransactions) {
            if (!transaction.isReturned()) {
                System.out.println(transaction);
            }
        }
    }


    public static boolean checkCustomerEligibility(Customer customer) {
        // To check if the customer has any active rentals
        for (RentalTransaction transaction : rentalTransactions) {
            if (transaction.getCustomer().equals(customer) && !transaction.isReturned()) {
                return false;
            }
        }
        return true;
    }

    public static List<Vehicle> searchVehiclesByModel(String modelName) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.getModel().equalsIgnoreCase(modelName)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

//            To find a vehicle
            public static Vehicle findVehicleById(String vehicleId) {
                for (Vehicle vehicle : vehicleFleet) {
                    if (vehicle.getVehicleId().equals(vehicleId)) {
                        return vehicle;
                    }
                }
                return null;
            }

    }