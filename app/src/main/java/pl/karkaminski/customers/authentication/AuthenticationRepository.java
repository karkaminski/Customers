package pl.karkaminski.customers.authentication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AuthenticationRepository {

    private MutableLiveData<LoginResult> result = new MutableLiveData<LoginResult>();
    private MutableLiveData<User> user = new MutableLiveData<>(null);

    public LiveData<LoginResult> login (String username, String password) {
        if (username.equals("Admin") || password.equals("Admin")) {
            result.postValue(new LoginResult(true));
            user = new MutableLiveData<>(new User(username, password));
        }
        return result;
    }

    public LiveData<User> user () {
        return user;
    }


}
