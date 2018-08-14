package id.co.flashcome.introandroidstudio.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.User;

/**
 * Created by kakaroto on 07/08/18.
 */
public class LoginActivity extends AppCompatActivity {

    private String TAG = "Login Activity";
    private EditText etEmail, etPassword;
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER = "user";
    private final int REQUEST_REGISTER = 2;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = new User();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        Log.d(TAG, "onCreate: di panggil");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: di panggil");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: di panggil");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: di panggil");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: di panggil");
    }

    public void doLogin(View view) {
//        Toast.makeText(LoginActivity.this, "Tombol Login di tekan !", Toast.LENGTH_SHORT).show();
        String email = etEmail.getText().toString(), password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email Harus di isi !");
            etEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password minimal 6 !");
            etPassword.requestFocus();
            return;
        }

        if (!(email.equals(user.getEmail()) && password.equals(user.getPassword()))) {
            Toast.makeText(this, "Email / Password salah !", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "doLogin: " + user.toString());

        Log.d(TAG, "email :  " + email + " password: " + password);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_EMAIL, email);
        intent.putExtra(KEY_PASSWORD, password);

        intent.putExtra(KEY_USER, new User(email, password));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void goRegister(View view) {
        startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_REGISTER);
    }


     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RegisterActivity.RESULT_REGISTER && requestCode == REQUEST_REGISTER) {
            user = data.getParcelableExtra(KEY_USER);
            Log.d(TAG, "onActivityResult: " + user.toString());
        }
    }
}
