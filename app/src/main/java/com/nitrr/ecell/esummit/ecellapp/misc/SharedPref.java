package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SharedPref {

    public void setSharedPref(Context context, String access_token,
                              String firstName, String lastName,
                              String email) {

        getEditor(context).putString("access_token", access_token)
                .putString("firstName", firstName)
                .putString("lastName", lastName)
                .putString("email", email)
                .apply();
    }


    public void setIsLoggedIn(Context context, boolean isLoggedIn) {
        getEditor(context).putBoolean("isLoggedIn", isLoggedIn).apply();
    }

    public void setMobileVerified(Context context, boolean isVerified) {
        getEditor(context).putBoolean("mobileVerification", isVerified).apply();
    }

    public boolean getMobileVerified(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mobileVerification", false);
    }

    public String getAccessToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("access_token", "");
    }

    public String getFirstName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("firstName", "");
    }

    public String getLastName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public void setEmail(Context context, String email) {
        getEditor(context).putString("email", email).apply();
    }

    public String getEmail(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("email", "");
    }

    public boolean isLoggedIn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isLoggedIn", false);
    }

    private SharedPreferences.Editor getEditor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    public void clearPrefs(Activity activity) {
        getEditor(activity).clear().putBoolean("isLoggedIn", false).apply();
    }

    public void setAccessToken(Context context, String accessToken) {
        getEditor(context).putString("access_token",accessToken).apply();
    }

    public boolean isGreeted(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("greeted",false);
    }

    public void setGreeted(Activity activity, boolean isGreeted) {
        getEditor(activity).putBoolean("greeted", isGreeted).apply();
    }

    public void setDates(Context context, String startDate, String endDate) {
        getEditor(context).putString("start_date", startDate)
                .putString("end_date", endDate)
                .apply();
    }

    public String[] getDates(Context context) {
        return new String[]{PreferenceManager.getDefaultSharedPreferences(context).getString("start_date", context.getResources().getString(R.string.startDate)),
                    PreferenceManager.getDefaultSharedPreferences(context).getString("end_date", context.getResources().getString(R.string.endDate))};
    }
}
