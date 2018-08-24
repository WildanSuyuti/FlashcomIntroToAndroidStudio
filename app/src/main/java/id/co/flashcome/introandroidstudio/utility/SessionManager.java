package id.co.flashcome.introandroidstudio.utility;

import android.content.Context;
import android.content.SharedPreferences;

import id.co.flashcome.introandroidstudio.model.User;

/**
 * Created by kakaroto on 23/08/18.
 */
public class SessionManager {

    private static SessionManager sessionManager;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String
            NAMA = "nama",
            EMAIL = "email",
            PASSWORD = "password",
            IS_LOGGED_IN = "is-logged-in";

    public SessionManager(Context context) {
        final int PRIVATE_MODE = 0;
        preferences = context.getSharedPreferences("auth-preferences", PRIVATE_MODE);
        editor = preferences.edit();
    }

    public static void init(Context context) {
        sessionManager = new SessionManager(context);
    }

    public synchronized static SessionManager getInstance() {
        return sessionManager;
    }

    public void setNama(String nama) {
        editor.putString(NAMA, nama);
        editor.commit();
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public void setUser(User user) {
        editor.putString(NAMA, user.getNama());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PASSWORD, user.getPassword());
        editor.commit();
    }

    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public String getNama() {
        return preferences.getString(NAMA, "");
    }

    public String getPassword() {
        return preferences.getString(PASSWORD, "");
    }

    public String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public User getUser() {
        String nama = preferences.getString(NAMA, "");
        String email = preferences.getString(EMAIL, "");
        String password = preferences.getString(PASSWORD, "");
        return new User(nama, email, password);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }


}
