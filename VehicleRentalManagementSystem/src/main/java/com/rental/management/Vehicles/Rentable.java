package com.rental.management.Vehicles;
import com.rental.management.Customers.Customer;

public interface Rentable {

//    To rent the vehicle to a customer for specific number of days
    void rent(Customer customer, int days);

//    To return the vehicle
    void returnVehicle();
}
