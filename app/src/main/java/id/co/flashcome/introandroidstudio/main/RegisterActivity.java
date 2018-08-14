package id.co.flashcome.introandroidstudio.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etPassword;
    private String nama, email, password;
    public static final int RESULT_REGISTER = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNama = findViewById(R.id.et_nama);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        getSupportActionBar().setTitle("");

    }

    public void doRegister(View view) {
        nama = etNama.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama Harus di isi !");
            etNama.requestFocus();
            return;
        }

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

        Intent intent = new Intent();
        User user = new User(nama, email, password);
        intent.putExtra("user", user);
        setResult(RESULT_REGISTER, intent);
        finish();

    }
}
