package pl.karkaminski.customers.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "CustomerClassifications")
public class CustomerClassification implements Parcelable{

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

    protected CustomerClassification(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<CustomerClassification> CREATOR = new Creator<CustomerClassification>() {
        @Override
        public CustomerClassification createFromParcel(Parcel in) {
            return new CustomerClassification(in);
        }

        @Override
        public CustomerClassification[] newArray(int size) {
            return new CustomerClassification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
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

    @Override
    public String toString() {
        return name;
    }
}