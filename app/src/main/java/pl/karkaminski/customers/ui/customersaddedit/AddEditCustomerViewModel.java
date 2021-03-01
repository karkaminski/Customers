package pl.karkaminski.customers.ui.customersaddedit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomersRepository;

public class AddEditCustomerViewModel extends AndroidViewModel {

    private CustomersRepository customersRepository;

    public AddEditCustomerViewModel(@NonNull Application application) {
        super(application);
        customersRepository = new CustomersRepository(application);
    }

    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return customersRepository.getAllCustomerClassifications();
    }
}
