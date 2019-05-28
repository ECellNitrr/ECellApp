package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nitrr.ecell.esummit.ecellapp.R;

public class PrefUtils {
    private Activity activity;

    public PrefUtils(Activity activity) {
        this.activity = activity;
    }

    public void setAccessToken(String accessToken) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("access_token", accessToken);
        editor.apply();
    }

    public String getAccessToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("access_token", null);
    }

    public void clearPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    public boolean getIsLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isLoggedIn", false);
    }

    public void setIsFBLoggedIn(boolean isFBLoggedIn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFBLoggedIn", isFBLoggedIn);
        editor.apply();
    }

    public boolean getIsFBLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isFBLoggedIn", false);
    }

    public void setIsGLoggedIn(boolean isGLoggedIn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFBLoggedIn", isGLoggedIn);
        editor.apply();
    }

    public boolean getIsGLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getBoolean("isGLoggedIn", false);
    }


}
