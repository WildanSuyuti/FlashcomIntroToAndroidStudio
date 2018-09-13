package id.co.flashcome.introandroidstudio.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
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

import butterknife.BindView;
import butterknife.ButterKnife;
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

    //    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    //    private BottomifyNavigationView bottomifyNavigationView;
//    private TabLayout tabLayout;
    private ViewPagerTabAdapter pagerTabAdapter;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.bottomify_nav)
    BottomifyNavigationView bottomifyNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new InboxFragment());
        pagerAdapter.addFragment(new ProfileFragment());
//        viewPager.setAdapter(pagerAdapter);

//        tabLayout = findViewById(R.id.tablayout);
        pagerTabAdapter = new ViewPagerTabAdapter(getSupportFragmentManager());
        pagerTabAdapter.addFragment(new InboxFragment(), "Inbox");
        pagerTabAdapter.addFragment(new ProfileFragment(), "Profile");
//        pagerTabAdapter.addFragment(null, "Kosong");
        viewPager.setAdapter(pagerTabAdapter);
        tabLayout.setupWithViewPager(viewPager);

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

/*        getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                new InboxFragment()).commit();*/

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


//        bottomifyNavigationView = findViewById(R.id.bottomify_nav);

        bottomifyNavigationView.setOnNavigationItemChangedListener(new OnNavigationItemChangeListener() {
            @Override
            public void onNavigationItemChanged(BottomifyNavigationView.NavigationItem navigationItem) {
                switch (navigationItem.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                }
               /* Toast.makeText(NavigationActivity.this, "posisi : "
                        + navigationItem.getPosition(), Toast.LENGTH_SHORT).show();
                switch (navigationItem.getPosition()) {
                    case 0:
//                        openFragment(new InboxFragment());
                        break;
                    case 1:
//                        openFragment(new ProfileFragment());
                        break;
                    default:
                        Toast.makeText(NavigationActivity.this, "Logout !", Toast.LENGTH_SHORT).show();
                        break;
                }*/

            }
        });

//        bottomifyNavigationView.setActiveNavigationIndex(viewPager.getCurrentItem());
//        Log.d(NavigationActivity.class.getSimpleName(), "onCreate adapter fragment posisi : " + pagerAdapter.getFragmentPosition());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(NavigationActivity.class.getSimpleName(), "onPageScrolled: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
//            openFragment(new InboxFragment());
        } else if (id == R.id.nav_profile) {
//            openFragment(new ProfileFragment());
        } else if (id == R.id.nav_logout) {
            SessionManager.getInstance().clear();
            startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/*    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                fragment).commit();
    }*/

/*    public void openInbox(View view) {
        openFragment(new InboxFragment());
    }*/


}
