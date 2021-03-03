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

    private CustomerClassification classificationTemp = new CustomerClassification();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AddEditClassificationFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        if (getArguments() != null) {
            args = AddEditClassificationFragmentArgs.fromBundle(getArguments());
            if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
                binding.textViewTitle.setText("Edit Customer Classification");
                binding.buttonDelete.setVisibility(View.VISIBLE);
                classificationTemp = args.getCustomerClassification();
                binding.editTextName.setText(classificationTemp.getName());
                binding.editTextDescription.setText(classificationTemp.getDescription());
            }
            if (args.getMessage() == ClassificationsFragment.ADD_ELEMENT) {
                binding.buttonDelete.setVisibility(View.GONE);
                binding.textViewTitle.setText("Add Customer Classification");
            }
        }

        binding.buttonSave.setOnClickListener(view -> saveData());

        binding.buttonDelete.setOnClickListener(v -> {
            mViewModel.delete(classificationTemp);
            getActivity().onBackPressed();
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

        classificationTemp.setName(name);
        classificationTemp.setDescription(description);

        if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
            classificationTemp.setId(args.getCustomerClassification().getId());
            mViewModel.update(classificationTemp);
        }
        if (args.getMessage() == ClassificationsFragment.ADD_ELEMENT) {
            mViewModel.insert(classificationTemp);
        }
        getActivity().onBackPressed();
    }
}