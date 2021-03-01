package pl.karkaminski.customers.ui.mainview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
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
        binding.tabLayout.setupWithViewPager(binding.viewPager);

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