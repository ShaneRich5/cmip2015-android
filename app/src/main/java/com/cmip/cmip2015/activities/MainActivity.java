package com.cmip.cmip2015.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.adapters.AppBarSpinnerAdapter;
import com.cmip.cmip2015.adapters.ApplianceAdapter;
import com.cmip.cmip2015.fragments.NavDrawerFragment;
import com.cmip.cmip2015.listeners.RecyclerItemClickListener;
import com.cmip.cmip2015.logs.Logger;
import com.cmip.cmip2015.pojo.Appliance;
import com.cmip.cmip2015.pojo.SpinnerNavItem;
import com.cmip.cmip2015.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private Toolbar toolbar;
    private ApplianceAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private NavDrawerFragment mDrawerFragment;
    private int totalSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupDrawer();
        setupSpinner();
        setupRecyclerView();
    }

    private void setupSpinner() {
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.app_bar_spinner,
                toolbar, false);

        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);

        AppBarSpinnerAdapter spinnerAdapter = new AppBarSpinnerAdapter(this);
        spinnerAdapter.addItems(getSpinnerData());

        Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private List<SpinnerNavItem> getSpinnerData() {
        List<SpinnerNavItem> items = new ArrayList<>();
        items.add(new SpinnerNavItem("Light"));
        items.add(new SpinnerNavItem("Medium"));
        items.add(new SpinnerNavItem("Heavy"));
        return items;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ApplianceAdapter(this);
        mAdapter.setAppliances(Appliance.initialData());

        mRecyclerView = (RecyclerView) findViewById(R.id.list_appliances);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Logger.toastShort(this, "Light");
                mAdapter.setAppliances(Appliance.lightBundle());
                mAdapter.notifyDataSetChanged();
                break;
            case 1:
                Logger.toastShort(this, "Medium");
                mAdapter.setAppliances(Appliance.mediumBundle());
                mAdapter.notifyDataSetChanged();
                break;
            case 2:
                Logger.toastShort(this, "Heavy");
                mAdapter.setAppliances(Appliance.heavyBundle());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
