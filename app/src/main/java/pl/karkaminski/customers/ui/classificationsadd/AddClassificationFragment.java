package pl.karkaminski.customers.ui.classificationsadd;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.karkaminski.customers.R;

public class AddClassificationFragment extends Fragment {

    private AddClassificationViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddClassificationViewModel.class);

        return inflater.inflate(R.layout.add_classification_fragment, container, false);
    }
}