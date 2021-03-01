package pl.karkaminski.customers.ui.mainview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import pl.karkaminski.customers.databinding.ViewPagerFragmentBinding;
import pl.karkaminski.customers.ui.classifications.ClassificationsFragment;
import pl.karkaminski.customers.ui.customers.CustomersFragment;

public class ViewPagerFragment extends Fragment {

    ViewPagerFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ViewPagerFragmentBinding.inflate(inflater, container, false);

        binding.viewPager.setAdapter(
                new FragmentStateAdapter(getActivity().getSupportFragmentManager(), getLifecycle()) {
                    @NonNull
                    @Override
                    public Fragment createFragment(int position) {

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
                    public int getItemCount() {
                        return 2;
                    }
                }
        );

        new TabLayoutMediator(binding.tabs, binding.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setText("Customers");
                                break;
                            case 1:
                                tab.setText("Classifications");
                                break;
                        }
                    }
                }).attach();


        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}