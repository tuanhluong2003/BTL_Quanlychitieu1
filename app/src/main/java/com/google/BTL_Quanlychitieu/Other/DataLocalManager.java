package com.google.BTL_Quanlychitieu.Other;

import android.content.Context;

import java.util.ConcurrentModificationException;

public class DataLocalManager {
    private static final String MUC_CHI_TIEU = "MUC_CHI_TIEU";
    private static DataLocalManager instance;
    private MysharedPreferences mysharedPreferences;

    public static void init(Context context)
    {
        instance = new DataLocalManager();
        instance.mysharedPreferences = new MysharedPreferences(context);
    }

    public static void update_mucchitieu(int value)
    {
        instance.mysharedPreferences.pushintvalue(MUC_CHI_TIEU, value);
    }

    public static int get_mucchitieu()
    {
        return instance.mysharedPreferences.getIntvalue(MUC_CHI_TIEU);
    }



}
