package pl.karkaminski.customers.ui.customersaddedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.databinding.AddEditClassificationFragmentBinding;
import pl.karkaminski.customers.databinding.AddEditCustomerFragmentBinding;
import pl.karkaminski.customers.ui.customers.CustomersFragment;

public class AddEditCustomerFragment extends Fragment {

    public static final String BUNDLE_KEY = "customer_with_classification";

    private AddEditCustomerFragmentBinding binding = null;
    private AddEditCustomerFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddEditCustomerFragmentBinding.inflate(inflater, container, false);

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


}