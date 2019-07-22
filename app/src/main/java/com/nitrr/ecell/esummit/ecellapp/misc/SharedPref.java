package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private SharedPreferences.Editor editor;

    private boolean isLoggedIn = false,
            isFBLoggedIn = false,
            isGLoggedIn = false;

    public void setSharedPref(Activity act, String access_token,
                              String firstName, String lastName,
                              String email, String contact,
                              String avatar, String facebook,
                              String linkedin) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        editor = prefs.edit();

        editor.putString("accessToken", access_token);
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.putString("email", email);
        editor.putString("contact", contact);
        editor.putString("avatar", avatar);
        editor.putString("facebook", facebook);
        editor.putString("linkedin", linkedin);
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putBoolean("isFBLoggedIn", isFBLoggedIn);
        editor.putBoolean("isGLoggedIn", isGLoggedIn);

        editor.apply();
        editor.commit();
    }

    public void setIsLoggedIn( boolean isLoggedIn, boolean isFBLoggedIn, boolean isGLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        this.isFBLoggedIn = isFBLoggedIn;
        this.isGLoggedIn = isGLoggedIn;
    }

    public String getAccessToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("token", "");
    }

    public String getFirstName(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("firstName", "");
    }

    public String getLastName(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("lastName", "");
    }

    public String getEmail(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("email", "");
    }

    public String getContact(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("lastName", "");
    }

    public String getAvatar(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("lastName", "");
    }

    public String getFacebook(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("lastName", "");
    }

    public String getLinkedin(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("lastName", "");
    }

    public boolean isLoggedIn(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("isLoggedIn", false);
    }

    SharedPreferences.Editor getEditor(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).edit();
    }

    void clearPrefs(Activity activity) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(activity).edit();
        editor.clear();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        editor.commit();
    }

    public void setAccessToken(Context context, String accessToken) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
        editor.putString("access_token",accessToken);
        editor.apply();
        editor.commit();
    }
}
