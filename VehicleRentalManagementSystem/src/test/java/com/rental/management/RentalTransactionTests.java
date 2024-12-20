package com.rental.management;
import com.rental.management.Transactions.RentalTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rental.management.Customers.Customer;
import com.rental.management.Vehicles.Car;
import java.time.LocalDate;

public class RentalTransactionTests {

    @Test
    public void testConstructorAndGetters() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        RentalTransaction transaction = new RentalTransaction(customer, car, 5, 250.0);

        assertNotNull(transaction.getTransactionId());
        assertEquals(customer, transaction.getCustomer());
        assertEquals(car, transaction.getVehicle());
        assertEquals(5, transaction.getRentalDays());
        assertEquals(250.0, transaction.getRentalCost());
        assertEquals(LocalDate.now(), transaction.getRentalDate());
    }

    @Test
    public void testSetRentalDaysZero() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        assertThrows(IllegalArgumentException.class, () -> new RentalTransaction(customer, car, 0, 250.0));
    }

    @Test
    public void testSetRentalCostNegative() {
        Customer customer = new Customer("C001", "Jennifer Banibensu", "jennifer@example.com", "233000000000");
        Car car = new Car("V001", "SUV", 50.0, true, true, true, true, true);
        assertThrows(IllegalArgumentException.class, () -> new RentalTransaction(customer, car, 5, -100.0));
    }
}
