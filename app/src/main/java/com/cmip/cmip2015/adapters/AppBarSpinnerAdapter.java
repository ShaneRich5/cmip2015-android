package com.cmip.cmip2015.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.pojo.SpinnerNavItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/24/2015.
 */
public class AppBarSpinnerAdapter extends BaseAdapter {
    private List<SpinnerNavItem> mItems = new ArrayList<>();
    private TextView tvTitle;
    private Context context;

    public AppBarSpinnerAdapter(Context context) {
        this.context = context;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(SpinnerNavItem navItem) {
        mItems.add(navItem);
    }

    public void addItems(List<SpinnerNavItem> navItems) {
        mItems.addAll(navItems);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_nav_row, null);
        }

        tvTitle = (TextView) convertView.findViewById(R.id.title);

        tvTitle.setText(mItems.get(position).getName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_nav_row, null);
        }

        tvTitle = (TextView) convertView.findViewById(R.id.title);
        tvTitle.setText(mItems.get(position).getName());
        return convertView;
    }
}
