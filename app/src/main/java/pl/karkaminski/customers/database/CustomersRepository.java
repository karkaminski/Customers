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

    public void delete(CustomerClassification... customerClassification) {
        new DeleteCustomerClassificationsAsyncTask(customerClassificationDao).execute(customerClassification);
    }

    ////Customers
    public LiveData<List<CustomerWithClassification>> getAllCustomersWithClassification() {
        return customerDao.getAllWithClassification();
    }

    public void insert(Customer... customers){
        new InsertCustomersAsyncTask(customerDao).execute(customers);
    }

    public void update(Customer customer){
        new UpdateCustomerAsyncTask(customerDao).execute(customer);
    }

    public void delete(Customer... customers) {
        new DeleteCustomerAsyncTask(customerDao).execute(customers);
    }

    //////CLASSIFICATIONS ASYNC TASKS starts here///////
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

    private static class DeleteCustomerClassificationsAsyncTask extends AsyncTask<CustomerClassification, Void, Void> {

        private CustomerClassificationDao dao;

        public DeleteCustomerClassificationsAsyncTask(CustomerClassificationDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomerClassification... customerClassifications) {
            dao.delete(customerClassifications[0]);
            return null;
        }
    }

    //////CUSTOMERS ASYNC TASKS starts here///////

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

    private static class UpdateCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {

        private CustomerDao dao;

        public UpdateCustomerAsyncTask(CustomerDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            dao.update(customers[0]);
            return null;
        }
    }

    private static class DeleteCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {

        private CustomerDao dao;

        public DeleteCustomerAsyncTask(CustomerDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            dao.delete(customers);
            return null;
        }
    }
}
