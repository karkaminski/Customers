package pl.karkaminski.customers.ui.classifications;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import pl.karkaminski.customers.database.CustomerClassification;

public class CustomerClassificationTableDataAdapter extends TableDataAdapter<CustomerClassification> {

    public CustomerClassificationTableDataAdapter(Context context, List<CustomerClassification> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final TextView textView = new TextView(getContext());

        switch (columnIndex) {
            case 0:
                textView.setText(getItem(rowIndex).getName());
                break;
            case 1:
                textView.setText(getItem(rowIndex).getDescription());
                break;
        }
        return textView;
    }
}
