package pl.karkaminski.customers.ui.classificationsaddedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.databinding.AddEditClassificationFragmentBinding;
import pl.karkaminski.customers.ui.SharedViewModel;
import pl.karkaminski.customers.ui.classifications.ClassificationsFragment;

public class AddEditClassificationFragment extends Fragment {

    private AddEditClassificationFragmentBinding binding = null;
    private AddEditClassificationFragmentArgs args;
    private SharedViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AddEditClassificationFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        if (getArguments() != null) {
            args = AddEditClassificationFragmentArgs.fromBundle(getArguments());
            if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
                final CustomerClassification customerClassification;
                customerClassification = args.getCustomerClassification();
                binding.editTextName.setText(customerClassification.getName());
                binding.editTextDescription.setText(customerClassification.getDescription());
            }
        }

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        return binding.getRoot();
    }

    private void saveData() {
        String name = binding.editTextName.getText().toString();
        String description = binding.editTextDescription.getText().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert name and description", Toast.LENGTH_SHORT).show();
            return;
        }

        final CustomerClassification cc = new CustomerClassification();
        cc.setName(name);
        cc.setDescription(description);

        if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
            cc.setId(args.getCustomerClassification().getId());
            mViewModel.updateClassification(cc);
        }
        if (args.getMessage() == ClassificationsFragment.ADD_ELEMENT) {
            mViewModel.insertClassification(cc);
        }
        getActivity().onBackPressed();
    }
}