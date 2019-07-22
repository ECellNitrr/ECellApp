package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private Activity activity;
    private SharedPreferences.Editor editor;
    private String accessToken = null,
            firstName = null,
            lastName = null,
            email = null,
            contact = null,
            avatar = null,
            facebook = null,
            linkedin = null;

    private boolean isLoggedIn = false,
            isFBLoggedIn = false,
            isGLoggedIn = false;

    public void setSharedPref(Activity act, String access_token,
                              String firstName, String lastName,
                              String email, String contact,
                              String avatar, String facebook,
                              String linkedin) {
        activity = act;
        this.accessToken = access_token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.avatar = avatar;
        this.facebook = facebook;
        this.linkedin = linkedin;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = prefs.edit();

        editor.putString("token", access_token);
        editor.putString("first_name", firstName);
        editor.putString("last_name", lastName);
        editor.putString("email", email);
        editor.putString("contact", contact);
        editor.putString("avatar", avatar);
        editor.putString("facebook", facebook);
        editor.putString("linkedin", linkedin);
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putBoolean("isFBLoggedIn", isFBLoggedIn);
        editor.putBoolean("isGLoggedIn", isGLoggedIn);

        editor.apply();
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isFBLoggedIn() {
        return isFBLoggedIn;
    }

    public boolean isGLoggedIn() {
        return isGLoggedIn;
    }

    SharedPreferences.Editor getEditor(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).edit();
    }


    void clearPrefs() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(activity).edit();
        editor.clear();
        editor.apply();
    }

    public void setAccessToken(String accessToken,Context context) {
        this.accessToken = accessToken;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
        editor.putString("token",accessToken);
        editor.apply();
        editor.commit();

    }
}
