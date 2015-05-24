package com.cmip.cmip2015.pojo;

import com.cmip.cmip2015.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/23/2015.
 */
public class Appliance implements Serializable {
    private long id;
    private String name;
    private double watts;
    private double hours;
    private int amount;
    private int icon = R.drawable.ic_launcher;

    public Appliance() {
    }

    public Appliance(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Appliance(long id, String name, double watts) {
        this.id = id;
        this.name = name;
        this.watts = watts;
    }

    public Appliance(long id, String name, double watts, double hours, int amount) {
        this.id = id;
        this.name = name;
        this.watts = watts;
        this.hours = hours;
        this.amount = amount;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getWatts() {
        return watts;
    }

    public void setWatts(double watts) {
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

    public double toKWH(double watts) {
        return (watts / 1000);
    }

    public double toMonthly(double watts) {
        return watts * 30;
    }

    public static ArrayList<Appliance> initialData() {
        ArrayList<Appliance> appliances = new ArrayList<>();

        appliances.add(new Appliance(1, "Fan", 30, 4, 2));
        appliances.add(new Appliance(2, "Computer", 60, 4, 2));
        appliances.add(new Appliance(3, "Air Conditioner", 1500, 4, 1));
        appliances.add(new Appliance(4, "Vacuum", 350, 1, 1));
        appliances.add(new Appliance(5, "Heater", 700, 1, 1));
        appliances.add(new Appliance(6, "Television", 30, 5, 2));
        appliances.add(new Appliance(7, "Printer", 40, 1, 1));
        appliances.add(new Appliance(8, "Light Bulb", 180, 5, 5));
        appliances.add(new Appliance(9, "Deep Freezer", 50, 24, 1));
        appliances.add(new Appliance(10, "Washing Machine", 40, 2, 1));

        return appliances;
    }

    public static ArrayList<Appliance> lightBundle() {
        ArrayList<Appliance> appliances = new ArrayList<>();

        appliances.add(new Appliance(1, "Fan", 30, 2, 1));
        appliances.add(new Appliance(2, "Computer", 60, 8, 1));
        appliances.add(new Appliance(3, "Air Conditioner", 1500, 4, 1));
        appliances.add(new Appliance(4, "Vacuum", 350, 2, 1));
        appliances.add(new Appliance(5, "Heater", 700, 2, 1));
        appliances.add(new Appliance(6, "Television", 30, 8, 2));
        appliances.add(new Appliance(7, "Printer", 40, 1, 1));
        appliances.add(new Appliance(8, "Light Bulb", 180, 7, 5));
        appliances.add(new Appliance(9, "Deep Freezer", 50, 24, 1));
        appliances.add(new Appliance(10, "Washing Machine", 40, 2, 1));

        return appliances;
    }

    public static ArrayList<Appliance> mediumBundle() {
        ArrayList<Appliance> appliances = new ArrayList<>();

        appliances.add(new Appliance(1, "Fan", 30, 4, 2));
        appliances.add(new Appliance(2, "Computer", 60, 7, 2));
        appliances.add(new Appliance(3, "Air Conditioner", 1500, 8, 1));
        appliances.add(new Appliance(4, "Vacuum", 350, 2, 1));
        appliances.add(new Appliance(5, "Heater", 700, 4, 1));
        appliances.add(new Appliance(6, "Television", 30, 7, 3));
        appliances.add(new Appliance(7, "Printer", 40, 2, 1));
        appliances.add(new Appliance(8, "Light Bulb", 180, 8, 9));
        appliances.add(new Appliance(9, "Deep Freezer", 50, 24, 1));
        appliances.add(new Appliance(10, "Washing Machine", 40, 5, 1));

        return appliances;
    }

    public static ArrayList<Appliance> heavyBundle() {
        ArrayList<Appliance> appliances = new ArrayList<>();

        appliances.add(new Appliance(1, "Fan", 30, 7, 4));
        appliances.add(new Appliance(2, "Computer", 60, 9, 5));
        appliances.add(new Appliance(3, "Air Conditioner", 1500, 7, 2));
        appliances.add(new Appliance(4, "Vacuum", 350, 2, 2));
        appliances.add(new Appliance(5, "Heater", 700, 2, 3));
        appliances.add(new Appliance(6, "Television", 30, 7, 5));
        appliances.add(new Appliance(7, "Printer", 40, 2, 3));
        appliances.add(new Appliance(8, "Light Bulb", 180, 7, 13));
        appliances.add(new Appliance(9, "Deep Freezer", 50, 24, 2));
        appliances.add(new Appliance(10, "Washing Machine", 40, 3, 2));

        return appliances;
    }
}
