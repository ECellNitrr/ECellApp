package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SharedPref {
    private Activity activity;

    public SharedPref(Activity activity, Bundle details,
                      String accessToken,
                      boolean isLoggedIn,
                      boolean isFBLoggedIn,
                      boolean isGLoggedIn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        String fristname = details.getString("fristname");
        String lastname = details.getString("lastname");
        String email = details.getString("email");

        editor.putString("access_token", accessToken);
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putBoolean("isFBLoggedIn", isFBLoggedIn);
        editor.putBoolean("isGLoggedIn", isGLoggedIn);
        editor.apply();
        this.activity = activity;
    }

    public String getAccessToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("access_token", null);
    }

    public boolean getIsLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isLoggedIn", false);
    }

    public boolean getIsFBLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isFBLoggedIn", false);
    }

    public boolean getIsGLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isGLoggedIn", false);
    }

    public void clearPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
