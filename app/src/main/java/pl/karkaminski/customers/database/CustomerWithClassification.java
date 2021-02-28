package pl.karkaminski.customers.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CustomerWithClassification implements Parcelable {

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

    protected CustomerWithClassification(Parcel in) {
        customerClassification = in.readParcelable(CustomerClassification.class.getClassLoader());
    }

    public static final Creator<CustomerWithClassification> CREATOR = new Creator<CustomerWithClassification>() {
        @Override
        public CustomerWithClassification createFromParcel(Parcel in) {
            return new CustomerWithClassification(in);
        }

        @Override
        public CustomerWithClassification[] newArray(int size) {
            return new CustomerWithClassification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(customerClassification, i);
    }
}
