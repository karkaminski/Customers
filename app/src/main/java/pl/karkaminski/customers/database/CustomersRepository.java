package pl.karkaminski.customers.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomersRepository {

    private CustomerClassificationDao customerClassificationDao;
    private CustomerDao customerDao;

    public CustomersRepository(Application application) {
        CustomersDatabase database = CustomersDatabase.getInstance(application);
        customerClassificationDao = database.customerClassificationDao();
        customerDao = database.customerDao();
    }

    ////Customers Classifications
    public LiveData<List<CustomerClassification>> getAllCustomerClassifications() {
        return customerClassificationDao.getAll();
    }

    public void insert(CustomerClassification... customerClassifications){
        new InsertCustomerClassificationsAsyncTask(customerClassificationDao).execute(customerClassifications);
    }

    public void update(CustomerClassification customerClassification) {
        new UpdateCustomerClassificationsAsyncTask(customerClassificationDao).execute(customerClassification);
    }

    ////Customers
    public LiveData<List<CustomerWithClassification>> getAllCustomersWithClassification() {
        return customerDao.getAllWithClassification();
    }

    public void insert(Customer... customers){
        new InsertCustomersAsyncTask(customerDao).execute(customers);
    }

    ////Async Tasks
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

    private static class UpdateCustomerClassificationsAsyncTask extends AsyncTask<CustomerClassification, Void, Void> {

        private CustomerClassificationDao dao;

        public UpdateCustomerClassificationsAsyncTask(CustomerClassificationDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomerClassification... customerClassifications) {
            dao.update(customerClassifications[0]);
            return null;
        }
    }

    private static class InsertCustomersAsyncTask extends AsyncTask<Customer, Void, Void> {

        private CustomerDao dao;

        public InsertCustomersAsyncTask(CustomerDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            dao.insert(customers);
            return null;
        }
    }
}
