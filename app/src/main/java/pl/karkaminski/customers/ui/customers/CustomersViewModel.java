package pl.karkaminski.customers.ui.customers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.karkaminski.customers.database.Customer;
import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.database.CustomersRepository;

public class CustomersViewModel extends AndroidViewModel {

    private CustomersRepository customersRepository;

    public CustomersViewModel(@NonNull Application application) {
        super(application);
        customersRepository = new CustomersRepository(application);
    }

    public LiveData<List<Customer>> getAllCustomers(){
        return customersRepository.getAllCustomers();
    }

    public LiveData<List<CustomerWithClassification>> getAllCustomersWithClassification() {
        return customersRepository.getAllCustomersWithClassification();
    }
}