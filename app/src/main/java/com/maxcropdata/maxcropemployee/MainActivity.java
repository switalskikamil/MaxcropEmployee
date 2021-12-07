package com.maxcropdata.maxcropemployee;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.maxcropdata.maxcropemployee.view.AccountSettingsFragment;
import com.maxcropdata.maxcropemployee.view.MainMenuFragment;
import com.maxcropdata.maxcropemployee.view.ServerSettingsFragment;
import com.maxcropdata.maxcropemployee.view.ShowDataFilterFragment;
import com.maxcropdata.maxcropemployee.view.ShowIssuesFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(MainMenuFragment.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void loadFragment(Fragment f) {
        loadFragment(f, true);
    }

    public void loadFragment(Fragment f, boolean saveToStack) {
        //boolean allowedWithoutLoggin = false;
        if (f != null) {
            /*if (this.dataHolder.getLoggedInScannerProfile() == null
                    && !allowedWithoutLoggin
                    && this.getDataHolder().getScannerProfiles().size() > 0) {
                Toast.makeText(this, R.string.loggin_to_profile, Toast.LENGTH_SHORT).show();
            } else {*/

                FragmentManager fManager = getSupportFragmentManager();
                fManager.beginTransaction().replace(R.id.nav_host_fragment, f, f.getTag()).addToBackStack(f.getTag()).commitAllowingStateLoss();

                /*if (f instanceof McmFragmentInterface)
                    getSupportActionBar().setTitle(((McmFragmentInterface) f).getTitle());
                else getSupportActionBar().setTitle("McmFragmentInterface???");*/

                //dataHolder.setCurrentFragment(f);
               // this.dataHolder.getFragmentHistoryManager().movingTo(f, saveToStack);
            //}
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_main) {
            loadFragment(MainMenuFragment.getInstance());
        } else if (id == R.id.nav_account) {
            loadFragment(AccountSettingsFragment.getInstance());
        } else if (id == R.id.nav_filter_data) {
            loadFragment(ShowDataFilterFragment.getInstance());
        } else if (id == R.id.nav_server) {
            loadFragment(ServerSettingsFragment.getInstance());
        } else if (id == R.id.nav_issue) {
            loadFragment(ShowIssuesFragment.getInstance());
        } else {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
