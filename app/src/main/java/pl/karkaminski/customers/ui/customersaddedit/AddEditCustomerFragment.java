package pl.karkaminski.customers.ui.customersaddedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.karkaminski.customers.databinding.AddEditClassificationFragmentBinding;
import pl.karkaminski.customers.databinding.AddEditCustomerFragmentBinding;

public class AddEditCustomerFragment extends Fragment {

    private AddEditCustomerFragmentBinding binding = null;

    public static AddEditCustomerFragment newInstance() {
        return new AddEditCustomerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddEditCustomerFragmentBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }


}