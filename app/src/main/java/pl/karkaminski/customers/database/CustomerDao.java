package pl.karkaminski.customers.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM Customers")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM Customers")
    LiveData<List<CustomerWithClassification>> getAllWithClassification();

    @Insert
    void insert(Customer... customers);

}
