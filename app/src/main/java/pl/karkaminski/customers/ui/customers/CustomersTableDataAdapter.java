package pl.karkaminski.customers.ui.customers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import pl.karkaminski.customers.CustomersApplication;
import pl.karkaminski.customers.database.CustomerWithClassification;

public class CustomersTableDataAdapter extends TableDataAdapter<CustomerWithClassification> {

    public static final String EMPTY_CELL = "---";

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
                        getItem(rowIndex).getCustomerClassification() != null ? String.valueOf(getItem(rowIndex).getCustomerClassification().getName()) : EMPTY_CELL
                );
                break;
            case 3:
                textView.setText(
                        getItem(rowIndex).getCustomer().getNip() != null ? getItem(rowIndex).getCustomer().getNip() : EMPTY_CELL);
                break;
            case 4:
                textView.setText(
                        getItem(rowIndex).getCustomer().getCity() != null ? getItem(rowIndex).getCustomer().getCity() : EMPTY_CELL);
                break;
            case 5:
                if (getItem(rowIndex).getCustomer().getDateTime() != null) {
                    final Date dateTime = getItem(rowIndex).getCustomer().getDateTime();
                    final String dateString = CustomersApplication.globalDateFormat.format(dateTime);
                    textView.setText(dateString);
                } else textView.setText("- - -");
                break;
        }

        return textView;
    }
}
