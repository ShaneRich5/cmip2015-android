package com.cmip.cmip2015.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.activities.BundlesActivity;
import com.cmip.cmip2015.activities.MainActivity;
import com.cmip.cmip2015.adapters.NavigationDrawerAdapter;
import com.cmip.cmip2015.pojo.NavigationDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/24/2015.
 */
public class NavDrawerFragment extends Fragment {
    public static final String NAV_DRAWER_TAG = "nav_drawer_tag";
    public static final String PREF_FILE_NAME="drawer_pref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;

    private int current_position;
    private boolean mUserLearnedDrawer; // checks if the user if the user is aware of the navigation drawers existence
    private boolean mFromSavedInstanceState; // indicates whether the fragment has been started from the very first time or coming back from a rotation

    private View containerView;
    private RecyclerView recyclerView;

    private boolean isDrawerOpened = false;

    public NavDrawerFragment() {
        // Required empty public constructor
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false")); // checks if user has ever opened the drawer

        // check if activity is being recreated from a rotation
        if(savedInstanceState != null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new NavigationDrawerAdapter(getActivity(),getData(), current_position);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(getActivity(), "onClick" + position, Toast.LENGTH_SHORT).show();
                openActivity(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick" + position, Toast.LENGTH_SHORT).show();
            }
        }));
        return layout;
    }

    private void openActivity(int position) {
        switch(position) {
            case 0:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), BundlesActivity.class));
                break;
        }
    }

    public static List<NavigationDrawerItem> getData(){
        List<NavigationDrawerItem> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.ic_number3, R.drawable.ic_number4};
        String[] titles = {"Home", "Bundle", "Placeholer", "Placeholer"};

        for (int i = 0; i < titles.length && i < icons.length; i++)
        {
            NavigationDrawerItem currentItem = new NavigationDrawerItem();

            currentItem.setIconId(icons[i]);
            currentItem.setTitle(titles[i]);

            data.add(currentItem);
        }
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar, int pos) {
        current_position = pos;
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        if(!mUserLearnedDrawer&& !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    // remove private
    private static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        RecyclerTouchListener(final Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            Log.d(NAV_DRAWER_TAG, "RecyclerTouchListener constructor invoked");

            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d(NAV_DRAWER_TAG, "onSingleTapUp" + e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (child != null && clickListener != null)
                    {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                    Log.d(NAV_DRAWER_TAG, "onSingleTapUp" + e);
                    super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildLayoutPosition(child));
            }
            Log.d(NAV_DRAWER_TAG, "RecyclerTouchListener onInterceptTouchEvent " + gestureDetector.onTouchEvent(e));

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d(NAV_DRAWER_TAG, "RecyclerTouchListener onTouchEvent " + e);
        }
    }

    // change this to public
    private interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
}
