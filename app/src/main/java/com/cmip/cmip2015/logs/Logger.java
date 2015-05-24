package com.cmip.cmip2015.logs;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Shane on 5/23/2015.
 */
public class Logger {
    public static void m(String message) {
        Log.d("CMIP", "" + message);
    }

    public static void toastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
