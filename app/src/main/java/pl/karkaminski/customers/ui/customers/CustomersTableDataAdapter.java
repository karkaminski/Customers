package pl.karkaminski.customers.ui.customers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import pl.karkaminski.customers.database.Customer;

public class CustomersTableDataAdapter extends TableDataAdapter<Customer> {


    public CustomersTableDataAdapter(Context context, List<Customer> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final TextView textView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                textView.setText(String.valueOf(getItem(rowIndex).getId()));
                break;
            case 1:
                textView.setText(getItem(rowIndex).getName());
                break;
            case 2:
                textView.setText(
                        getItem(rowIndex).getNip() != null ? getItem(rowIndex).getNip() : "- - -"
                );
                break;
            case 3:
                textView.setText(
                        getItem(rowIndex).getCity() != null ? getItem(rowIndex).getCity() : "- - -"
                );
                break;
            case 4:
                textView.setText(
                        getItem(rowIndex).getDateTime() != null ? getItem(rowIndex).getDateTime().toString() : "- - -"
                );
                break;
        }

        return textView;
    }
}
