package pl.karkaminski.customers.authentication;

public class LoginResult {

    private boolean success = false;

    public LoginResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
