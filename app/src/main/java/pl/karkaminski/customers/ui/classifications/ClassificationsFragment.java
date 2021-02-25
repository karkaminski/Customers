package pl.karkaminski.customers.ui.classifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.databinding.ClassificationsFragmentBinding;

public class ClassificationsFragment extends Fragment {

    public static final String[] TABLE_HEADERS = {"ID", "Name", "Description"};

    private ClassificationsViewModel mViewModel;
    private ClassificationsFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ClassificationsFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ClassificationsViewModel.class);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(0, 3);
        columnModel.setColumnWeight(1, 4);
        columnModel.setColumnWeight(2, 6);
        binding.tableView.setColumnModel(columnModel);
        binding.tableView.setColumnComparator(0, new ClassificationIdComparator());
        binding.tableView.setColumnComparator(1, new ClassificationNameComparator());
        binding.tableView.setEmptyDataIndicatorView(binding.textViewNoData);
        binding.tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));

        List<CustomerClassification> customerClassifications = new ArrayList<>();
        CustomerClassificationTableDataAdapter adapter = new CustomerClassificationTableDataAdapter(getContext(), customerClassifications);
        binding.tableView.setDataAdapter(adapter);

        mViewModel.getAllCustomerClassifications().observe(getViewLifecycleOwner(), new Observer<List<CustomerClassification>>() {
            @Override
            public void onChanged(List<CustomerClassification> customerClassifications) {
                adapter.clear();
                adapter.addAll(customerClassifications);
                adapter.notifyDataSetChanged();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private static class ClassificationNameComparator implements Comparator<CustomerClassification>{
        @Override
        public int compare(CustomerClassification cc1, CustomerClassification cc2) {
            return cc1.getName().compareTo(cc2.getName());
        }
    }

    private static class ClassificationIdComparator implements Comparator<CustomerClassification> {
        @Override
        public int compare(CustomerClassification cc1, CustomerClassification cc2) {
            return String.valueOf(cc1.getId()).compareTo(String.valueOf(cc2.getId()));
        }
    }
}