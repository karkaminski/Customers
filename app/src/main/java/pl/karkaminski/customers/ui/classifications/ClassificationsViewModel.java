package pl.karkaminski.customers.ui.classifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomersRepository;

public class ClassificationsViewModel extends AndroidViewModel {

    private CustomersRepository repository;
    private LiveData<List<CustomerClassification>> allClassifications;

    public ClassificationsViewModel(@NonNull Application application) {
        super(application);
        repository = new CustomersRepository(application);
    }

    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return repository.getAllCustomerClassifications();
    }
}