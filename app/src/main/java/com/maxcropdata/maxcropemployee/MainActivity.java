package com.maxcropdata.maxcropemployee;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.model.account.AccountController;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.model.issue.IssueController;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.response.AccountAlreadyExistsException;
import com.maxcropdata.maxcropemployee.model.server.response.AccountLoginServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.AccountRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.ForbiddenActionException;
import com.maxcropdata.maxcropemployee.model.server.response.IssueRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.ReportsForDatesServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.RequestUnathorizedException;
import com.maxcropdata.maxcropemployee.model.server.response.ResponseMalformedException;
import com.maxcropdata.maxcropemployee.model.server.response.ServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.UexpectedResponseStatusException;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;
import com.maxcropdata.maxcropemployee.view.AccountSettingsFragment;
import com.maxcropdata.maxcropemployee.view.MainMenuFragment;
import com.maxcropdata.maxcropemployee.view.ServerSettingsFragment;
import com.maxcropdata.maxcropemployee.view.ShowDataFilterFragment;
import com.maxcropdata.maxcropemployee.view.ShowIssuesFragment;
import com.maxcropdata.maxcropemployee.view.ShowReportFragment;
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AsyncResponseProcessor {

    private Account userAccount;
    private Server server;
    private List<Issue> savedIssues;
    private List<Report> savedReports;

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

        loadSettings();

        loadFragment(MainMenuFragment.getInstance());
    }

    private void loadSettings() {
        try {
            //loads saved server or default server if nothing is saved
            this.server = ServerController.readServerFromFileSystem(this);

            //loads user account
            this.userAccount = AccountController.readAccountFromFileSystem(this);

            //load saved issues
            this.savedIssues = IssueController.readIssuesFromFileSystem(this);


        } catch (JSONException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //TODO: clean up
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void processFinish(ServerResponse response) {
        try {
            if (response instanceof ReportsForDatesServerResponse)
                processReportsForDatesServerResponse((ReportsForDatesServerResponse) response);
            else if (response instanceof AccountRegistrationServerResponse)
                processAccountRegistrationServerResponse((AccountRegistrationServerResponse) response);
            else if (response instanceof AccountLoginServerResponse)
                processAccountLoginServerResponse((AccountLoginServerResponse)response);
            else if (response instanceof IssueRegistrationServerResponse)
                processIssueRegistrationServerResponse((IssueRegistrationServerResponse)response);


        } catch (ForbiddenActionException e) {
            if (e.isRegistrationResponse()) MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_registration_forbidden));
            else MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_requested_date_not_available));
        } catch (AccountAlreadyExistsException e) {
            MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_account_already_registered));
        } catch (RequestUnathorizedException e) {
            MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_unathorized));
        } catch (ResponseMalformedException e) {
            MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_response_malformed));
        } catch (UexpectedResponseStatusException e) {
            if (e.getStatusCode() == 0) MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_connection_problem));
            else MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_response_unknown) + e.getMessage());
        } catch (IllegalAccessException e) {
            MCToast.displayText(this, Toast.LENGTH_LONG, getString(R.string.error_illegal_access_exception));
        }
    }

    private void processIssueRegistrationServerResponse(IssueRegistrationServerResponse response)
            throws RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException, IllegalAccessException, ForbiddenActionException {

        response.readResponse(this);

        IssueController.updateRegisteredIssues(this.savedIssues, response.getReceivedIssueRegistration());

        IssueController.saveIssuesToFileSystem(this, this.savedIssues);
    }

    private void processAccountLoginServerResponse(AccountLoginServerResponse response)
            throws RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException,
            IllegalAccessException, ForbiddenActionException {
        response.readResponse(this);

        AccountController.mergeWithLoginResponse(userAccount, response);

        AccountController.saveAccountToFileSystem(this, userAccount);

        loadFragment(MainMenuFragment.getInstance());
    }

    private void processReportsForDatesServerResponse(ReportsForDatesServerResponse response)
            throws UexpectedResponseStatusException,
            ResponseMalformedException,
            RequestUnathorizedException,
            AccountAlreadyExistsException, ForbiddenActionException {
        response.readResponse(this);

        Report report = response.getReport();

        savedReports = new ArrayList<>();
        savedReports.add(report);

        loadFragment(ShowReportFragment.getInstance(report));
    }

    private void processAccountRegistrationServerResponse(AccountRegistrationServerResponse response)
            throws UexpectedResponseStatusException,
            ResponseMalformedException,
            RequestUnathorizedException,
            AccountAlreadyExistsException,
            IllegalAccessException, ForbiddenActionException {
        response.readResponse(this);

        if (userAccount == null) userAccount = new Account();

        AccountController.mergeWithRegistrationResponse(userAccount, response);

        AccountController.saveAccountToFileSystem(this, userAccount);

        loadFragment(MainMenuFragment.getInstance());
    }

    public Account getUserAccount() {
        return this.userAccount;
    }

    public Server getServer() {
        return this.server;
    }

    public void setUserAccount(Account account) {
        this.userAccount = account;
    }

    public List<Issue> getSavedIssues() {
        return savedIssues;
    }
}
