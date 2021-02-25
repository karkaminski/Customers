package pl.karkaminski.customers.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerClassificationDao {

    @Query("SELECT * FROM CustomerClassifications")
    LiveData<List<CustomerClassification>> getAll();

    @Insert
    void insert(CustomerClassification... customerClassification);

    @Delete
    void delete(CustomerClassification customerClassification);


}
