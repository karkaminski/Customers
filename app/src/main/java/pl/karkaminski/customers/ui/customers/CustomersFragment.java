package pl.karkaminski.customers.ui.customers;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import pl.karkaminski.customers.R;
import pl.karkaminski.customers.database.Customer;
import pl.karkaminski.customers.databinding.ClassificationsFragmentBinding;
import pl.karkaminski.customers.databinding.CustomersFragmentBinding;

public class CustomersFragment extends Fragment {

    public static final String[] TABLE_HEADERS = {"ID", "Name", "NIP", "City"};

    private CustomersViewModel mViewModel;
    private CustomersFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CustomersFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CustomersViewModel.class);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(0, 3);
        columnModel.setColumnWeight(1, 4);
        columnModel.setColumnWeight(2, 4);
        columnModel.setColumnWeight(3, 4);
        binding.tableView.setEmptyDataIndicatorView(binding.textViewNoData);

        binding.tableView.setColumnModel(columnModel);
        binding.tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));

        List<Customer> customers = new ArrayList<>();
        CustomersTableDataAdapter adapter = new CustomersTableDataAdapter(getContext(), customers);
        binding.tableView.setDataAdapter(adapter);

        mViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                adapter.clear();
                adapter.addAll(customers);
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
}