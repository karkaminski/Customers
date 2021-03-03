package pl.karkaminski.customers.ui.customersaddedit;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import pl.karkaminski.customers.CustomersApplication;
import pl.karkaminski.customers.R;
import pl.karkaminski.customers.database.Customer;
import pl.karkaminski.customers.database.CustomerClassification;
import pl.karkaminski.customers.database.CustomerWithClassification;
import pl.karkaminski.customers.databinding.AddEditCustomerFragmentBinding;
import pl.karkaminski.customers.ui.SharedViewModel;
import pl.karkaminski.customers.ui.customers.CustomersFragment;

public class AddEditCustomerFragment extends Fragment {

    public static final String BUNDLE_KEY = "customer_with_classification";

    private SharedViewModel mViewModel;
    private AddEditCustomerFragmentBinding binding = null;
    private AddEditCustomerFragmentArgs args;

    private MutableLiveData<CustomerWithClassification> customerWithClassificationLiveData =
            new MutableLiveData<>(new CustomerWithClassification());
    private Customer customerTemp = new Customer();


    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    private SwitchDateTimeDialogFragment dateTimeFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddEditCustomerFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        setupSpinner();


        customerWithClassificationLiveData.observe(getViewLifecycleOwner(), customerWithClassification -> {
            if (customerWithClassification.getCustomer() != null) {

                //Setup Name EditText
                if (customerWithClassification.getCustomer().getName() != null) {
                    binding.editTextName.setText(customerWithClassification.getCustomer().getName());
                }
                //Setup Classification EditText
                if (customerWithClassification.getCustomer().getClassificationId() != null && customerWithClassification.getCustomerClassification() != null) {
                    binding.spinnerTextView.setText(customerWithClassification.getCustomerClassification().getName(), false);
                }

                //Setup NIP EditText
                if (customerWithClassification.getCustomer().getNip() != null) {
                    binding.editTextNip.setText(customerWithClassification.getCustomer().getNip());
                }

                //Setup City EditText
                if (customerWithClassification.getCustomer().getCity() != null) {
                    binding.editTextCity.setText(customerWithClassification.getCustomer().getCity());
                }

                //Setup Date EditText
                if (customerWithClassification.getCustomer().getDateTime() != null) {
                    final Date date = customerWithClassification.getCustomer().getDateTime();
                    final String dateString = CustomersApplication.globalDateFormat.format(date);
                    binding.editTextDate.setText(dateString);
                }
            }
        });

        setupDateTimePicker();

        binding.editTextDate.setInputType(InputType.TYPE_NULL);
        binding.editTextDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            final Date date = calendar.getTime();
            dateTimeFragment.startAtCalendarView();
            dateTimeFragment.setDefaultDateTime(date);
            dateTimeFragment.show(getParentFragmentManager(), TAG_DATETIME_FRAGMENT);
        });

        if (getArguments() != null) {
            args = AddEditCustomerFragmentArgs.fromBundle(getArguments());

            if (args.getMessage() == CustomersFragment.EDIT_ELEMENT) {
                binding.textViewTitle.setText("Edit Customer");
                binding.buttonDelete.setVisibility(View.VISIBLE);
                customerWithClassificationLiveData.postValue(args.getCustomer());
                customerTemp = args.getCustomer().getCustomer();
            }

            if (args.getMessage() == CustomersFragment.ADD_ELEMENT) {
                binding.textViewTitle.setText("Add Customer");
                binding.buttonDelete.setVisibility(View.GONE);
            }
        }

        binding.buttonSave.setOnClickListener(v -> saveData());

        binding.buttonDelete.setOnClickListener(v -> {
            mViewModel.delete(customerTemp);
            getActivity().onBackPressed();
        });
        return binding.getRoot();
    }

    private void setupDateTimePicker() {
        // Construct SwitchDateTimePicker
        dateTimeFragment = (SwitchDateTimeDialogFragment) getParentFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if (dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
            );
        }
        dateTimeFragment.setTimeZone(TimeZone.getDefault());
        dateTimeFragment.set24HoursMode(true);
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                customerTemp.setDateTime(date);
                final String dateString = CustomersApplication.globalDateFormat.format(date);
                binding.editTextDate.setText(dateString);

            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Do nothing
            }
        });
    }

    private void setupSpinner() {
        List<CustomerClassification> customerClassifications = new ArrayList<>();
        ArrayAdapter<CustomerClassification> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                customerClassifications
        );
        binding.spinnerTextView.setAdapter(adapter);

        mViewModel.getAllCustomerClassifications().observe(getViewLifecycleOwner(), new Observer<List<CustomerClassification>>() {
            @Override
            public void onChanged(List<CustomerClassification> customerClassifications) {
                adapter.clear();
                adapter.addAll(customerClassifications);
                adapter.notifyDataSetChanged();
                if (!(customerClassifications.size()>0)){
                    binding.spinnerTextView.setEnabled(false);
                    binding.spinnerTextView.setText("[no classifications available]");
                }
            }
        });

        binding.spinnerTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customerTemp.setClassificationId(customerClassifications.get(position).getId());
            }
        });
    }

    private void saveData() {
        String name = binding.editTextName.getText().toString();
        String nip = binding.editTextNip.getText().toString();
        String city = binding.editTextCity.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert name", Toast.LENGTH_SHORT).show();
            return;
        }

        customerTemp.setName(name);
        customerTemp.setNip(nip);
        customerTemp.setCity(city);

        if (args.getMessage() == CustomersFragment.EDIT_ELEMENT) {
            mViewModel.update(customerTemp);
        }
        if (args.getMessage() == CustomersFragment.ADD_ELEMENT) {
            mViewModel.insert(customerTemp);
        }
        getActivity().onBackPressed();
    }
}