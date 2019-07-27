package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private SharedPreferences.Editor editor;

    private boolean isLoggedIn = false,
            isFBLoggedIn = false,
            isGLoggedIn = false;

    public void setSharedPref(Context act, String access_token,
                              String firstName, String lastName,
                              String email, String contact,
                              String avatar, String facebook,
                              String linkedin) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        editor = prefs.edit();

        editor.putString("access_token", access_token);
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

    public void setIsLoggedIn(boolean isLoggedIn, boolean isFBLoggedIn, boolean isGLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        this.isFBLoggedIn = isFBLoggedIn;
        this.isGLoggedIn = isGLoggedIn;
    }

    public void setMobileVerified(Context context, boolean isVerified) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean("mobileVerification", isVerified);
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

    public String getContact(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public String getAvatar(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public String getFacebook(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public String getLinkedin(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public boolean isLoggedIn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isLoggedIn", false);
    }

    private SharedPreferences.Editor getEditor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    void clearPrefs(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
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

    public boolean isGreeted(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("greeted",false);
    }

    public void setGreeted(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean("greeted",true);
        editor.commit();
    }
}
