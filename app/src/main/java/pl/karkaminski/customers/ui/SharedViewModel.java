package pl.karkaminski.customers.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.karkaminski.customers.database.Customer;
import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.database.CustomersRepository;

public class SharedViewModel extends AndroidViewModel {

    private CustomersRepository customersRepository;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        customersRepository = new CustomersRepository(application);
    }

    public LiveData<List<CustomerWithClassification>> getAllCustomersWithClassification() {
        return customersRepository.getAllCustomersWithClassification();
    }

    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return customersRepository.getAllCustomerClassifications();
    }

    public void insert(CustomerClassification customerClassification) {
        customersRepository.insert(customerClassification);
    }

    public void update(CustomerClassification customerClassification) {
        customersRepository.update(customerClassification);
    }

    public void insert(Customer customer) {
        customersRepository.insert(customer);
    }

    public void update(Customer customer) {
        customersRepository.update(customer);
    }

    public void delete(Customer customer) {
        customersRepository.delete(customer);
    }
}
