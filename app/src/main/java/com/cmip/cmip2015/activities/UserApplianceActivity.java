package com.cmip.cmip2015.activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.adapters.ApplianceAdapter;
import com.cmip.cmip2015.databases.ApplianceDatabase;
import com.cmip.cmip2015.fragments.NavDrawerFragment;
import com.cmip.cmip2015.pojo.Appliance;
import com.cmip.cmip2015.views.DividerItemDecoration;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class UserApplianceActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ApplianceAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private NavDrawerFragment mDrawerFragment;
    private FloatingActionButton fab;
    private TextView tvEmptyRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appliance);

        tvEmptyRecycler = (TextView) findViewById(R.id.empty_rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ApplianceDatabase db = new ApplianceDatabase(this);
        ArrayList<Appliance> data = db.readAppliances();

        setupToolbar();
        setupDrawer();
        setupRecyclerView(data);
        setupFAB();
    }

    private void setupFAB() {

        fab.attachToRecyclerView(mRecyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserApplianceActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_appliance_ativity, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(ArrayList<Appliance> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ApplianceAdapter(this);

        if (data == null) {
            tvEmptyRecycler.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            mAdapter.setAppliances(data);
            tvEmptyRecycler.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list_appliances);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupDrawer() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerFragment = (NavDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar, 0);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }
}
