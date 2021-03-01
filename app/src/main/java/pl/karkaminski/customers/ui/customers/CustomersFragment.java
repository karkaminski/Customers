package pl.karkaminski.customers.ui.customers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.databinding.CustomersFragmentBinding;
import pl.karkaminski.customers.ui.SharedViewModel;
import pl.karkaminski.customers.ui.mainview.ViewPagerFragmentDirections;

public class CustomersFragment extends Fragment {

    public static final String ADD_ELEMENT = "add_customer_item";
    public static final String EDIT_ELEMENT = "remove_customer_item";

    public static final String[] TABLE_HEADERS = {"Name", "Classification","Date"};

    private SharedViewModel mViewModel;
    private CustomersFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CustomersFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(0, 1);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        binding.tableView.setEmptyDataIndicatorView(binding.textViewNoData);

        binding.tableView.setColumnModel(columnModel);
        binding.tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));

        List<CustomerWithClassification> customers = new ArrayList<>();
        CustomersTableDataAdapter adapter = new CustomersTableDataAdapter(getContext(), customers);
        binding.tableView.setDataAdapter(adapter);

        mViewModel.getAllCustomersWithClassification().observe(getViewLifecycleOwner(), new Observer<List<CustomerWithClassification>>() {
            @Override
            public void onChanged(List<CustomerWithClassification> customers) {
                adapter.clear();
                adapter.addAll(customers);
                adapter.notifyDataSetChanged();
            }
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPagerFragmentDirections.ActionViewPagerFragmentToAddCustomerFragment action =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToAddCustomerFragment(null);
                action.setMessage(ADD_ELEMENT);

                NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(action);
            }
        });

        binding.tableView.addDataLongClickListener(new TableDataLongClickListener<CustomerWithClassification>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, CustomerWithClassification clickedData) {
                ViewPagerFragmentDirections.ActionViewPagerFragmentToAddCustomerFragment action =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToAddCustomerFragment(clickedData);
                action.setMessage(EDIT_ELEMENT);

                NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(action);
                return true;
            }
        });

        binding.tableView.addDataClickListener(new TableDataClickListener<CustomerWithClassification> () {
            @Override
            public void onDataClicked(int rowIndex, CustomerWithClassification clickedData) {
                //TODO show details of CustomerWithClassification
            }
        });

        getParentFragmentManager().setFragmentResultListener(ADD_ELEMENT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //TODO add element
            }
        });

        getParentFragmentManager().setFragmentResultListener(EDIT_ELEMENT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //TODO Edit element
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