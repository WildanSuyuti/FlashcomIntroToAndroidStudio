package id.co.flashcome.introandroidstudio.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.volcaniccoder.bottomify.BottomifyNavigationView;
import com.volcaniccoder.bottomify.OnNavigationItemChangeListener;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.auth.LoginActivity;
import id.co.flashcome.introandroidstudio.feature.ProfileFragment;
import id.co.flashcome.introandroidstudio.feature.inbox.InboxActivity;
import id.co.flashcome.introandroidstudio.feature.inbox.InboxFragment;
import id.co.flashcome.introandroidstudio.model.User;
import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView tvName = header.findViewById(R.id.tv_name);
        TextView tvEmail = header.findViewById(R.id.tv_email);

        DatabaseHandler db = DatabaseHandler.getInstance();
        if (!db.getAllUser().isEmpty()) {
            User user = DatabaseHandler.getInstance().getUser(SessionManager.getInstance().getEmail());
            if (user != null) {
                tvName.setText(user.getNama());
                tvEmail.setText(user.getEmail());
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                new InboxFragment()).commit();

/*        FrameLayout frmInbox = findViewById(R.id.frm_inbox);
        frmInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new InboxFragment());
            }
        });*/

       /* FrameLayout frmProfile = findViewById(R.id.frm_profile);
        frmProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ProfileFragment());
            }
        });

        FrameLayout frmLogout = findViewById(R.id.frm_logout);
        frmLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationActivity.this, "Ini Akan Keluar !", Toast.LENGTH_SHORT).show();
            }
        });*/

        BottomifyNavigationView bottomifyNavigationView = findViewById(R.id.bottomify_nav);
        bottomifyNavigationView.setOnNavigationItemChangedListener(new OnNavigationItemChangeListener() {
            @Override
            public void onNavigationItemChanged(BottomifyNavigationView.NavigationItem navigationItem) {
                Toast.makeText(NavigationActivity.this, "haloo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Halo !!!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inbox) {
//            startActivity(new Intent(NavigationActivity.this, InboxActivity.class));
            openFragment(new InboxFragment());
        } else if (id == R.id.nav_profile) {
            openFragment(new ProfileFragment());
        } else if (id == R.id.nav_logout) {
            SessionManager.getInstance().clear();
            startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                fragment).commit();
    }

/*    public void openInbox(View view) {
        openFragment(new InboxFragment());
    }*/


}
