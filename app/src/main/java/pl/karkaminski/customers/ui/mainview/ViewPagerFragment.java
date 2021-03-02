package pl.karkaminski.customers.ui.mainview;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import pl.karkaminski.customers.R;
import pl.karkaminski.customers.authentication.User;
import pl.karkaminski.customers.authentication.UserViewModel;
import pl.karkaminski.customers.databinding.ViewPagerFragmentBinding;
import pl.karkaminski.customers.ui.classifications.ClassificationsFragment;
import pl.karkaminski.customers.ui.customers.CustomersFragment;

public class ViewPagerFragment extends Fragment {

    ViewPagerFragmentBinding binding = null;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        binding = ViewPagerFragmentBinding.inflate(inflater, container, false);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        userViewModel.user().observe(getViewLifecycleOwner(), (Observer<User>) user -> {
            if (user == null) {
                NavHostFragment.findNavController(getParentFragmentManager().getPrimaryNavigationFragment()).navigate(R.id.loginFragment);
            }
        });

        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new CustomersFragment();
                    case 1:
                        return new ClassificationsFragment();
                    default:
                        return null;
                }
            }

            @Override
            public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Customers";
                    case 1:
                        return "Classifications";
                    default:
                        return null;
                }
            }



        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}