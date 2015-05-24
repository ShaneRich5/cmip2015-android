package com.cmip.cmip2015.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.pojo.Appliance;

import java.util.ArrayList;

/**
 * Created by Shane on 5/24/2015.
 */
public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.ViewHolderAppliance> {
    private ArrayList<Appliance> mApplianceList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ApplianceAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setAppliances(ArrayList<Appliance> appliances) {
        this.mApplianceList = appliances;
        notifyDataSetChanged();
    }

    public void addAppliances(ArrayList<Appliance> appliances) {
        this.mApplianceList.addAll(appliances);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderAppliance onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_appliance, parent, false);
        return new ViewHolderAppliance(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAppliance holder, int position) {
        Appliance currentAppliance = mApplianceList.get(position);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), currentAppliance.getIcon());

        holder.thumbnail.setImageBitmap(bitmap);
        holder.title.setText(currentAppliance.getName());
        holder.watts.setText("Watts: " + currentAppliance.getWatts());

    }

    @Override
    public int getItemCount() {
        return mApplianceList.size();
    }

    public class ViewHolderAppliance extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView title, watts;

        public ViewHolderAppliance(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.appliance_thumbnail);
            title = (TextView) itemView.findViewById(R.id.appliance_title);
            watts = (TextView) itemView.findViewById(R.id.appliance_watts);
        }
    }
}
