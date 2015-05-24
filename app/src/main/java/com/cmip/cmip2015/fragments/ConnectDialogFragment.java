package com.cmip.cmip2015.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;
import com.cmip.cmip2015.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/24/2015.
 */
public class ConnectDialogFragment extends DialogFragment {

    public static String TAG = "ConnectFragment";

    private OnConnectListener mListener;

    public ConnectDialogFragment() {
    }

    public static ConnectDialogFragment newInstance() {
        ConnectDialogFragment fragment = new ConnectDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        View root = inflater.inflate(R.layout.fragment_connect_dialog, container, false);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);

        ViewPager pager = (ViewPager) root.findViewById(R.id.pager);

        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), getFragments()));

        tabs.setViewPager(pager);

        return root;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        fragmentList.add(LoginFragment.newInstance());
        fragmentList.add(RegisterFragment.newInstance());

        return fragmentList;
    }

    public interface OnConnectListener {
        void loginUser();
        void registerUser();
        void cancelConnect();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
            return (position == 0) ? LoginFragment.TITLE : RegisterFragment.TITLE;
        }
    }

}
