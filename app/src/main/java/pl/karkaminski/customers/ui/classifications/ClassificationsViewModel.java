package pl.karkaminski.customers.ui.classifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomersRepository;

public class ClassificationsViewModel extends AndroidViewModel {

    private CustomersRepository customersRepository;

    public ClassificationsViewModel(@NonNull Application application) {
        super(application);
        customersRepository = new CustomersRepository(application);
    }

    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return customersRepository.getAllCustomerClassifications();
    }

    public void insert (CustomerClassification customerClassification) {
        customersRepository.insert(customerClassification);
    }

    public void update (CustomerClassification customerClassification){
        customersRepository.update(customerClassification);
    }
}