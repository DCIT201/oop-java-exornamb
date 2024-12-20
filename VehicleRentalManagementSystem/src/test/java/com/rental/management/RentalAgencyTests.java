package com.rental.management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rental.management.Agency.RentalAgency;
import com.rental.management.Customers.Customer;
import com.rental.management.Vehicles.Car;
import com.rental.management.Exceptions.CustomerNotEligibleException;
import com.rental.management.Exceptions.VehicleNotAvailableException;

public class RentalAgencyTests {

    @Test
    public void testAddVehicle() {
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        RentalAgency.addVehicle(car);
        assertEquals(1, RentalAgency.getAllVehicles().size());
    }

    @Test
    public void testRemoveVehicle() {
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        RentalAgency.addVehicle(car);
        RentalAgency.removeVehicle("V001");
        assertEquals(0, RentalAgency.getAllVehicles().size());
    }

    @Test
    public void testRentVehicleNotAvailable() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        RentalAgency.addVehicle(car);

        assertThrows(VehicleNotAvailableException.class, () -> RentalAgency.rentVehicle(customer, "V001", 5));
    }

    @Test
    public void testRentVehicleNotEligible() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        RentalAgency.addVehicle(car);

        try {
            RentalAgency.rentVehicle(customer, "V001", 5);
        } catch (CustomerNotEligibleException | VehicleNotAvailableException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertThrows(CustomerNotEligibleException.class, () -> RentalAgency.rentVehicle(customer, "V001", 5));
    }

    @Test
    public void testGetAvailableVehicles() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car1 = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        Car car2 = new Car("V002", "V8", 60.0, false, true, true, true, true);
        RentalAgency.addVehicle(car1);
        RentalAgency.addVehicle(car2);

        assertEquals(1, RentalAgency.getAvailableVehicles().size());
    }
}
