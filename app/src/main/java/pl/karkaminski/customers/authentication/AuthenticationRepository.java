package pl.karkaminski.customers.authentication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AuthenticationRepository {

    private MutableLiveData<LoginResult> result = new MutableLiveData<>();
    private MutableLiveData<User> user = new MutableLiveData<>(null);

    public LiveData<LoginResult> login (String username, String password) {
        if (username.equals("Admin") && password.equals("Admin")) {
            result.postValue(LoginResult.SUCCESS);
            user = new MutableLiveData<>(new User(username, password));
        }
        else {
            result.postValue(LoginResult.ERROR);
        }
        return result;
    }

    public LiveData<User> user () {
        return user;
    }


}
