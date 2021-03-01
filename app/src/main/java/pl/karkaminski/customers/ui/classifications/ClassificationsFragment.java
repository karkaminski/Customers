package pl.karkaminski.customers.ui.classifications;

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
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.databinding.ClassificationsFragmentBinding;
import pl.karkaminski.customers.ui.SharedViewModel;
import pl.karkaminski.customers.ui.classificationsaddedit.AddEditClassificationFragment;
import pl.karkaminski.customers.ui.mainview.ViewPagerFragmentDirections;

public class ClassificationsFragment extends Fragment {

    public static final String ADD_ELEMENT = "add_customer_classification_item";
    public static final String EDIT_ELEMENT = "remove_customer_classification_item";

    public static final String[] TABLE_HEADERS = {"Name", "Description"};

    private SharedViewModel mViewModel;
    private ClassificationsFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ClassificationsFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(2);
        columnModel.setColumnWeight(0, 1);
        columnModel.setColumnWeight(1, 2);
        binding.tableView.setColumnModel(columnModel);
        binding.tableView.setEmptyDataIndicatorView(binding.textViewNoData);
        binding.tableView.setColumnComparator(0, new ClassificationNameComparator());
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

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPagerFragmentDirections.ActionViewPagerFragmentToAddClassificationFragment action =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToAddClassificationFragment(null);
                action.setMessage(ADD_ELEMENT);

                NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(action);
            }
        });

        binding.tableView.addDataLongClickListener(new TableDataLongClickListener<CustomerClassification>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, CustomerClassification clickedData) {
                ViewPagerFragmentDirections.ActionViewPagerFragmentToAddClassificationFragment action =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToAddClassificationFragment(clickedData);
                action.setMessage(EDIT_ELEMENT);

                NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(action);
                return true;
            }
        });

        binding.tableView.addDataClickListener(new TableDataClickListener<CustomerClassification>() {
            @Override
            public void onDataClicked(int rowIndex, CustomerClassification clickedData) {
                //TODO ??? Show details of CustomerClassification ???
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private static class ClassificationNameComparator implements Comparator<CustomerClassification> {
        @Override
        public int compare(CustomerClassification cc1, CustomerClassification cc2) {
            return cc1.getName().compareTo(cc2.getName());
        }
    }
}