package pl.karkaminski.customers.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomersRepository {

    private CustomerClassificationDao customerClassificationDao;
    private LiveData<List<CustomerClassification>> allCustomerClassifications;

    public CustomersRepository(Application application) {
        CustomersDatabase database = CustomersDatabase.getInstance(application);
        customerClassificationDao = database.customerClassificationDao();
        allCustomerClassifications = customerClassificationDao.getAll();
    }

    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return allCustomerClassifications;
    }

    public void insert(CustomerClassification... customerClassifications){
        new InsertCustomerClassificationsAsyncTask(customerClassificationDao).execute(customerClassifications);
    }

    private static class InsertCustomerClassificationsAsyncTask extends AsyncTask<CustomerClassification, Void, Void> {

        private CustomerClassificationDao dao;

        public InsertCustomerClassificationsAsyncTask(CustomerClassificationDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomerClassification... customerClassifications) {
            dao.insert(customerClassifications);
            return null;
        }
    }
}
