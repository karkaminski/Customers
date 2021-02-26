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

    public static final String BUNDLE_KEY = "customer_classification";

    private AddEditClassificationFragmentBinding binding = null;
    private AddEditClassificationFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AddEditClassificationFragmentBinding.inflate(inflater, container, false);

        CustomerClassification customerClassification = new CustomerClassification();

        if (getArguments() != null) {

            args = AddEditClassificationFragmentArgs.fromBundle(getArguments());

            if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT) {
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

        CustomerClassification cc = new CustomerClassification();
        cc.setName(name);
        cc.setDescription(description);
        if (args.getMessage() == ClassificationsFragment.EDIT_ELEMENT){
            cc.setId(args.getCustomerClassification().getId());
        }
        Bundle result = new Bundle();
        result.putParcelable(BUNDLE_KEY, cc);
        getParentFragmentManager().setFragmentResult(args.getMessage(), result);
        getActivity().onBackPressed();
    }
}