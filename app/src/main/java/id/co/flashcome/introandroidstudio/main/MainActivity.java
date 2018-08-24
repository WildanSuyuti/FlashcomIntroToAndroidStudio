package id.co.flashcome.introandroidstudio.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.auth.LoginActivity;
import id.co.flashcome.introandroidstudio.feature.inbox.InboxActivity;
import id.co.flashcome.introandroidstudio.model.User;
import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*        String email = getIntent().getStringExtra(LoginActivity.KEY_EMAIL);
        String password = getIntent().getStringExtra(LoginActivity.KEY_PASSWORD);
        User user = getIntent().getParcelableExtra(LoginActivity.KEY_USER);*/

/*        Button btnGoLogin = findViewById(R.id.btn_go_login);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

        TextView tvJudul = findViewById(R.id.tv_judul);
//        User user = SessionManager.getInstance().getUser();
        //mengambil data user dari database berdasarkan email yang sudah tersimpan di
        // sharedpreference ketika login
        User user = DatabaseHandler.getInstance().getUser(SessionManager.getInstance().getEmail());
        if (user != null) tvJudul.setText(user.toString());

/*        if (user != null)
            tvJudul.setText("Email: " + user.getEmail() + " password: " + user.getPassword());*/

        ImageView imageView = findViewById(R.id.iv_gambar);
        Glide.with(this).load("https://www.gstatic.com/webp/gallery3/1.png").into(imageView);
//        Glide.with(this).load(R.drawable.person).into(imageView);

        Log.d(TAG, "onCreate: is called ");

    }

    public void goInbox(View view) {
        startActivity(new Intent(MainActivity.this, InboxActivity.class));
    }

    public void logout(View view) {
        SessionManager.getInstance().clear();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: is called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: is called");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
