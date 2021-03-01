package pl.karkaminski.customers.ui.customersaddedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.databinding.AddEditClassificationFragmentBinding;
import pl.karkaminski.customers.databinding.AddEditCustomerFragmentBinding;
import pl.karkaminski.customers.ui.customers.CustomersFragment;

public class AddEditCustomerFragment extends Fragment {

    public static final String BUNDLE_KEY = "customer_with_classification";

    private AddEditCustomerViewModel mViewModel;
    private AddEditCustomerFragmentBinding binding = null;
    private AddEditCustomerFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddEditCustomerFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(AddEditCustomerViewModel.class);


        List<CustomerClassification> customerClassifications = new ArrayList<>();
        ArrayAdapter<CustomerClassification> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                customerClassifications
        );
        binding.spinnerClassification.setAdapter(adapter);

        mViewModel.getAllCustomerClassifications().observe(getViewLifecycleOwner(), new Observer<List<CustomerClassification>>() {
            @Override
            public void onChanged(List<CustomerClassification> customerClassifications) {
                adapter.clear();
                adapter.addAll(customerClassifications);
                adapter.notifyDataSetChanged();
            }
        });


        CustomerWithClassification customerWithClassification;
        if (getArguments() != null) {

            args = AddEditCustomerFragmentArgs.fromBundle(getArguments());

            if (args.getMessage() == CustomersFragment.EDIT_ELEMENT) {
                customerWithClassification = args.getCustomer();
                binding.editTextName.setText(customerWithClassification.getCustomer().getName());
                binding.editTextNip.setText(customerWithClassification.getCustomer().getNip());
                binding.editTextCity.setText(customerWithClassification.getCustomer().getCity());
            }
        }


        return binding.getRoot();
    }


};