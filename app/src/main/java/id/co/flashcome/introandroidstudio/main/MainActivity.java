package id.co.flashcome.introandroidstudio.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email = getIntent().getStringExtra(LoginActivity.KEY_EMAIL);
        String password = getIntent().getStringExtra(LoginActivity.KEY_PASSWORD);
        User user = getIntent().getParcelableExtra(LoginActivity.KEY_USER);

        TextView tvJudul = findViewById(R.id.tv_judul);
        if (user != null)
            tvJudul.setText("Email: " + user.getEmail() + " password: " + user.getPassword());

        ImageView imageView = findViewById(R.id.iv_gambar);
        Glide.with(this).load("https://www.gstatic.com/webp/gallery3/1.png").into(imageView);
//        Glide.with(this).load(R.drawable.person).into(imageView);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
