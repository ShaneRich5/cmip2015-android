package com.cmip.cmip2015.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.fragments.HeavyBundleFragment;
import com.cmip.cmip2015.fragments.LightBundleFragment;
import com.cmip.cmip2015.fragments.MediumBundleFragment;
import com.cmip.cmip2015.fragments.NavDrawerFragment;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Shane on 5/24/2015.
 */
public class BundlesActivity extends AppCompatActivity implements MaterialTabListener{
    public static final int TAB_LIGHT = 0;
    public static final int TAB_MEDIUM = 1;
    public static final int TAB_HEAVY = 2;

    private Toolbar toolbar;
    private ViewPager mPager;
    private MaterialTabHost mTabHost;
    private ViewPagerAdapter mAdapter;
    private NavDrawerFragment mDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundles);

        setupToolbar();
        setupDrawer();
        setupTabs();
    }

    private void setupTabs() {
        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getFragments());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i=0 ; i < mAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
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

    private List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(LightBundleFragment.newInstance(""));
        fragmentList.add(MediumBundleFragment.newInstance(""));
        fragmentList.add(HeavyBundleFragment.newInstance(""));

        return fragmentList;
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mPager.setCurrentItem((materialTab.getPosition()));
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragments;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.bundle_tab_titles)[position];
        }
    }
}
