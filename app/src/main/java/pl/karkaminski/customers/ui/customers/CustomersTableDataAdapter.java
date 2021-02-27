package pl.karkaminski.customers.ui.customers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import pl.karkaminski.customers.database.CustomerWithClassification;

public class CustomersTableDataAdapter extends TableDataAdapter<CustomerWithClassification> {


    public CustomersTableDataAdapter(Context context, List<CustomerWithClassification> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final TextView textView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                textView.setText(String.valueOf(getItem(rowIndex).getCustomer().getId()));
                break;
            case 1:
                textView.setText(getItem(rowIndex).getCustomer().getName());
                break;
            case 2:
                textView.setText(
                        getItem(rowIndex).getCustomerClassification() != null ? String.valueOf(getItem(rowIndex).getCustomerClassification().getName()) : "- - -"
                );
                break;
            case 3:
                textView.setText(
                        getItem(rowIndex).getCustomer().getNip() != null ? getItem(rowIndex).getCustomer().getNip() : "- - -"
                );
                break;
            case 4:
                textView.setText(
                        getItem(rowIndex).getCustomer().getCity() != null ? getItem(rowIndex).getCustomer().getCity() : "- - -"
                );
                break;
            case 5:
                textView.setText(
                        getItem(rowIndex).getCustomer().getDateTime() != null ? getItem(rowIndex).getCustomer().getDateTime().toString() : "- - -"
                );
                break;
        }

        return textView;
    }
}
