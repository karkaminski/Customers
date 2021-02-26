package pl.karkaminski.customers.ui.classificationsaddedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.databinding.AddEditClassificationFragmentBinding;
import pl.karkaminski.customers.ui.classifications.ClassificationsFragment;

public class AddEditClassificationFragment extends Fragment {

    private AddEditClassificationFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AddEditClassificationFragmentBinding.inflate(inflater, container, false);

        CustomerClassification customerClassification = new CustomerClassification();

        if (getArguments() != null) {

            AddEditClassificationFragmentArgs args = AddEditClassificationFragmentArgs.fromBundle(getArguments());

            if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
                customerClassification = args.getCustomerClassification();
                binding.editTextName.setText(customerClassification.getName());
                binding.editTextDescription.setText(customerClassification.getDescription());
            }

            if (args.getMessage() == ClassificationsFragment.ADD_ELEMENT) {
            }
        }


        return binding.getRoot();
    }
}