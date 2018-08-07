package id.co.flashcome.introandroidstudio.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.co.flashcome.introandroidstudio.R;

/**
 * Created by kakaroto on 07/08/18.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btn_login);
/*        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Tombol Login di tekan !", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void doLogin(View view){
        Toast.makeText(LoginActivity.this, "Tombol Login di tekan !", Toast.LENGTH_SHORT).show();
    }
}
