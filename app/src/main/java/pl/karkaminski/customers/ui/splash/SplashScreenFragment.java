package pl.karkaminski.customers.ui.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import pl.karkaminski.customers.databinding.SplashScreenFragmentBinding;

public class SplashScreenFragment extends Fragment {

    private SplashScreenFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SplashScreenFragmentBinding.inflate(inflater, container, false);

        binding.buttonStart.setOnClickListener(v -> {
            NavDirections action = SplashScreenFragmentDirections.actionSplashScreenFragmentToViewPagerFragment();
            NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(action);

        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}