package pl.karkaminski.customers.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "CustomerClassifications")
public class CustomerClassification {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ClassificationId")
    private int id;

    @NotNull
    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;

    //Room requires public constructor
    public CustomerClassification() {
    }

    public CustomerClassification(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}