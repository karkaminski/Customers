package pl.karkaminski.customers.database;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CustomerWithClassification {

    @Embedded private Customer customer;
    @Relation(
            parentColumn = "ClassificationId",
            entityColumn = "ClassificationId"
    )
    private CustomerClassification customerClassification;

    public CustomerWithClassification() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerClassification getCustomerClassification() {
        return customerClassification;
    }

    public void setCustomerClassification(CustomerClassification customerClassification) {
        this.customerClassification = customerClassification;
    }
}
