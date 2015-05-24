package com.cmip.cmip2015.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.pojo.NavigationDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Shane on 5/24/2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavigationViewHolder> {

    private int highlight_pos;
    private Context context;
    private LayoutInflater inflater;
    private List<NavigationDrawerItem> data = Collections.emptyList();

    public NavigationDrawerAdapter() {
    }

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data, int pos) {
        inflater = LayoutInflater.from(context);
        highlight_pos = pos;
        this.data = data;
        this.context = context;
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_navigation_drawer, viewGroup, false);
        return new NavigationViewHolder(view,  highlight_pos);
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder viewHolder, int i) {
        NavigationDrawerItem current = data.get(i);
        viewHolder.title.setText(current.getTitle());
        viewHolder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public NavigationViewHolder(View itemView, int highlight_position) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.navigation_list_text);
            icon = (ImageView) itemView.findViewById(R.id.navigation_list_icon);

            if (getPosition() == highlight_position)
                itemView.setBackgroundColor(context.getResources().getColor(R.color.primary_light));
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Item clicked at " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
