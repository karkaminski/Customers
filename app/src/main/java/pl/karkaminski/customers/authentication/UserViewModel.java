package pl.karkaminski.customers.authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository();
    }

    public LiveData<LoginResult> login (String username, String password) {
        return repository.login(username, password);
    }

    public LiveData<User> user(){
        return repository.user();
    }
}
