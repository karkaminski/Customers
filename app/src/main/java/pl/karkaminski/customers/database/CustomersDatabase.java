package pl.karkaminski.customers.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Database(entities = {CustomerClassification.class, Customer.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class CustomersDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "customers.db";
    private static CustomersDatabase instance;

    public abstract CustomerClassificationDao customerClassificationDao();
    public abstract CustomerDao customerDao();

    public static synchronized CustomersDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CustomersDatabase.class,
                    DATABASE_NAME)
                    .addCallback(exampleDataCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback exampleDataCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CustomerClassificationDao classificationDao;
        private CustomerDao customerDao;

        private PopulateDbAsyncTask(CustomersDatabase db){
            classificationDao = db.customerClassificationDao();
            customerDao = db.customerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<CustomerClassification> exampleClassifications = new ArrayList<>();
            ArrayList<Customer> exampleCustomers = new ArrayList<>();
            // Source:
            // https://www.customerexperienceinsight.com/there-are-4-types-of-customers-how-to-treat-each-one/
            exampleClassifications.add(new CustomerClassification("Price buyer", "These customers want to buy products and services only at the lowest possible price. They are less concerned about value, differentiation or relationships."));
            exampleClassifications.add(new CustomerClassification("Relationship buyer", "These customers want to trust and have dependable relationships with their suppliers, and they expect suppliers to take good care of them."));
            exampleClassifications.add(new CustomerClassification("Value buyer", "These customers understand value and want suppliers to be able to provide the most value in their relations."));
            exampleClassifications.add(new CustomerClassification("Poker player buyer", "These are relationship or value buyers who have learned that if they act like a price buyer, they can get high value for low prices."));
            classificationDao.insert(exampleClassifications.toArray(new CustomerClassification[exampleClassifications.size()]));

//                 * replaced by <code>Calendar.set(year + 1900, month, date)</code>

            Calendar calendar = Calendar.getInstance();

            exampleCustomers.add(new Customer(1, "Karol Kamiński", "1890213403", "Kraków", new Date(System.currentTimeMillis())));
            calendar.set(Calendar.YEAR, 2020);
            calendar.set(Calendar.DAY_OF_MONTH, 20);
            calendar.set(Calendar.MONTH, 11);
            exampleCustomers.add(new Customer(2,"Jan Kowalski", "7788236409","Warszawa", new Date(calendar.getTimeInMillis())));
            calendar.set(Calendar.YEAR, 2018);
            calendar.set(Calendar.DAY_OF_MONTH, 10);
            calendar.set(Calendar.MONTH, 06);
            exampleCustomers.add(new Customer(3,"Anna Nowak", "9234095834", "Gdańsk", new Date(calendar.getTimeInMillis())));
            calendar.set(Calendar.YEAR, 2021);
            calendar.set(Calendar.DAY_OF_MONTH, 03);
            calendar.set(Calendar.MONTH, 01);
            exampleCustomers.add(new Customer(4,"Andrzej Duda", "8098346821", "Kraków", new Date(calendar.getTimeInMillis())));
            customerDao.insert(exampleCustomers.toArray(new Customer[exampleCustomers.size()]));
            return null;
        }
    }
}
