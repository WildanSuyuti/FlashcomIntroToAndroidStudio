package id.co.flashcome.introandroidstudio.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.auth.LoginActivity;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

/**
 * Created by kakaroto on 16/08/18.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (SessionManager.getInstance().isLoggedIn()) {
                        startActivity(new Intent(SplashActivity.this, NavigationActivity.class));

                    } else startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        timerThread.start();
    }
}
