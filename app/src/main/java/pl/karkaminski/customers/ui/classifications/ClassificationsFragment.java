package pl.karkaminski.customers.ui.classifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomersRepository;
import pl.karkaminski.customers.databinding.ClassificationsFragmentBinding;

public class ClassificationsFragment extends Fragment {

    private ClassificationsViewModel mViewModel;
    private ClassificationsFragmentBinding binding = null;

    public static ClassificationsFragment newInstance() {
        return new ClassificationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ClassificationsFragmentBinding.inflate(inflater, container, false);

        CustomersRepository repository = new CustomersRepository(getActivity().getApplication());
        repository.getAllCustomerClassifications().observe(getViewLifecycleOwner(), new Observer<List<CustomerClassification>>() {
            @Override
            public void onChanged(List<CustomerClassification> customerClassifications) {
                String cc = "";
                for(CustomerClassification singleClassification : customerClassifications){
                    cc = cc + singleClassification.getId() + " ";
                    cc = cc + singleClassification.getName() + " ";
                    cc = cc + singleClassification.getDescription() + "\n";
                }
                binding.textView.setText(cc);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClassificationsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}