package pl.karkaminski.customers.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(tableName = "Customers")
public class Customer {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CustomerId")
    private int id;

    @ForeignKey(entity = CustomerClassification.class, parentColumns = "ClassificationId", childColumns = "ClassificationId", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "ClassificationId")
    private Integer classificationId;

    @NotNull
    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Nip")
    private String nip;

    @ColumnInfo(name = "City")
    private String city;

    @ColumnInfo(name = "DateTime")
    private Date dateTime;

    //Room requires this
    public Customer() {
    }

    public Customer(int classificationId, @NotNull String name, String nip, String city) {
        this.classificationId = classificationId;
        this.name = name;
        this.nip = nip;
        this.city = city;
    }

    public Customer(Integer classificationId, @NotNull String name, String nip, String city, Date dateTime) {
        this.classificationId = classificationId;
        this.name = name;
        this.nip = nip;
        this.city = city;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}
