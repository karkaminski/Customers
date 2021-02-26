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

import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.databinding.ClassificationsFragmentBinding;
import pl.karkaminski.customers.ui.classificationsaddedit.AddEditClassificationFragment;

public class ClassificationsFragment extends Fragment {

    public static final String ADD_ELEMENT = "add_customer_classification_item";
    public static final String EDIT_ELEMENT = "remove_customer_classification_item";

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

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassificationsFragmentDirections.ActionClassificationsFragmentToAddClassificationFragment action =
                        ClassificationsFragmentDirections.actionClassificationsFragmentToAddClassificationFragment(null);
                action.setMessage(ADD_ELEMENT);
                NavHostFragment.findNavController(getParentFragment()).navigate(action);
            }
        });

        binding.tableView.addDataLongClickListener(new TableDataLongClickListener<CustomerClassification>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, CustomerClassification clickedData) {
                ClassificationsFragmentDirections.ActionClassificationsFragmentToAddClassificationFragment action =
                        ClassificationsFragmentDirections.actionClassificationsFragmentToAddClassificationFragment(clickedData);
                action.setMessage(EDIT_ELEMENT);
                NavHostFragment.findNavController(getParentFragment()).navigate(action);
                return true;
            }
        });

//        binding.tableView.addDataClickListener(new TableDataClickListener<CustomerClassification>() {
//            @Override
//            public void onDataClicked(int rowIndex, CustomerClassification clickedData) {
//                ClassificationsFragmentDirections.ActionClassificationsFragmentToAddClassificationFragment action =
//                        ClassificationsFragmentDirections.actionClassificationsFragmentToAddClassificationFragment(clickedData);
//                action.setMessage(EDIT_ELEMENT);
//                NavHostFragment.findNavController(getParentFragment()).navigate(action);
//            }
//        });

        getParentFragmentManager().setFragmentResultListener(ADD_ELEMENT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                CustomerClassification cc = result.getParcelable(AddEditClassificationFragment.BUNDLE_KEY);
                mViewModel.insert(cc);
            }
        });

        getParentFragmentManager().setFragmentResultListener(EDIT_ELEMENT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                CustomerClassification cc = result.getParcelable(AddEditClassificationFragment.BUNDLE_KEY);
                mViewModel.update(cc);
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

    private static class ClassificationIdComparator implements Comparator<CustomerClassification> {
        @Override
        public int compare(CustomerClassification cc1, CustomerClassification cc2) {
            return String.valueOf(cc1.getId()).compareTo(String.valueOf(cc2.getId()));
        }
    }
}