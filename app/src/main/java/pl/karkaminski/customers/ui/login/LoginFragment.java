package pl.karkaminski.customers.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import pl.karkaminski.customers.authentication.LoginResult;
import pl.karkaminski.customers.authentication.UserViewModel;
import pl.karkaminski.customers.databinding.LoginFragmentBinding;

public class LoginFragment extends Fragment {

    public static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";

    private UserViewModel userViewModel;
    private SavedStateHandle savedStateHandle;
    private LoginFragmentBinding binding = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        Observer<LoginResult> observer = loginResult -> {
            if (loginResult == LoginResult.SUCCESS) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true);
                NavHostFragment.findNavController(getParentFragment()).popBackStack();
            } else {
                showErrorMessage();
            }
        };

        binding.buttonLogin.setOnClickListener((View.OnClickListener) v -> {
            String username = binding.editTextUsername.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            userViewModel.login(username, password).observe(getViewLifecycleOwner(), observer);

        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        savedStateHandle = Navigation.findNavController(view)
                .getPreviousBackStackEntry()
                .getSavedStateHandle();
        savedStateHandle.set(LOGIN_SUCCESSFUL, false);
    }

    private void showErrorMessage() {
        Toast.makeText(getContext(), "Wrong password or username", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}