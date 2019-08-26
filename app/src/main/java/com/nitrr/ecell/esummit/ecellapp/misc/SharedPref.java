package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nitrr.ecell.esummit.ecellapp.R;

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

    public void setIsVerifying(Context context, boolean isVerifying) {
        getEditor(context).putBoolean("verifying", isVerifying).apply();
    }

    public boolean getIsVerifying(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("verifying", false);
    }

    public void setIsBquizCancelable(Context context, boolean isBquizCancelable) {
        getEditor(context).putBoolean("cancelBquiz", isBquizCancelable).apply();
    }

    public boolean getIsBquizCancelable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("cancelBquiz", false);
    }

    public void setMobileNumber(Context context, String number) {
        getEditor(context).putString("mobile_number", number).apply();
    }

    public String getMobileNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("mobile_number", "");
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
        getEditor(activity).clear().putBoolean("isLoggedIn", false).apply();
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
