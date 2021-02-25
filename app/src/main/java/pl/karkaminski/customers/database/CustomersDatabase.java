package pl.karkaminski.customers.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {CustomerClassification.class}, version = 1)
public abstract class CustomersDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "customers.db";
    private static CustomersDatabase instance;

    public abstract CustomerClassificationDao customerClassificationDao();

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
        private CustomerClassificationDao dao;

        private PopulateDbAsyncTask(CustomersDatabase db){
            dao = db.customerClassificationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<CustomerClassification> exampleData = new ArrayList<>();
            // Source:
            // https://www.customerexperienceinsight.com/there-are-4-types-of-customers-how-to-treat-each-one/
            exampleData.add(new CustomerClassification("Price buyer", "These customers want to buy products and services only at the lowest possible price. They are less concerned about value, differentiation or relationships."));
            exampleData.add(new CustomerClassification("Relationship buyer", "These customers want to trust and have dependable relationships with their suppliers, and they expect suppliers to take good care of them."));
            exampleData.add(new CustomerClassification("Value buyer", "These customers understand value and want suppliers to be able to provide the most value in their relations."));
            exampleData.add(new CustomerClassification("Poker player buyer", "These are relationship or value buyers who have learned that if they act like a price buyer, they can get high value for low prices."));
            dao.insert(exampleData.toArray(new CustomerClassification[exampleData.size()]));
            return null;
        }
    }
}
