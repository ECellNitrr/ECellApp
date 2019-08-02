package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private SharedPreferences.Editor editor;

    private boolean isLoggedIn = false;

    public void setSharedPref(Context context, String access_token,
                              String firstName, String lastName,
                              String email) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();

        editor.putString("access_token", access_token);
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.putString("email", email);
        editor.putBoolean("isLoggedIn", isLoggedIn);

        editor.apply();
        editor.commit();
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setMobileVerified(Context context, boolean isVerified) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean("mobileVerification", isVerified);
        editor.apply();
        editor.commit();
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
        editor = getEditor(activity);
        editor.clear();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        editor.commit();
    }

    public void setAccessToken(Context context, String accessToken) {
        editor = getEditor(context);
        editor.putString("access_token",accessToken);
        editor.apply();
        editor.commit();
    }

    public boolean isGreeted(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("greeted",false);
    }

    public void setGreeted(Activity activity, boolean isGreeted) {
        SharedPreferences.Editor editor = getEditor(activity);
        editor.putBoolean("greeted", isGreeted);
        editor.apply();
        editor.commit();
    }
}
