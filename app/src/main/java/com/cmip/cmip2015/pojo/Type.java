package com.cmip.cmip2015.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Shane on 5/23/2015.
 */
public class Type implements Serializable {
    private long id;
    private String name;
    private double watts;

    public Type() {
    }

    public Type(long id, String name, double watts) {
        this.id = id;
        this.name = name;
        this.watts = watts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWatts() {
        return watts;
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }
}
