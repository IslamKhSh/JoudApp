package com.tremno.joudapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;

import java.util.Locale;

public class PreferenceUtils {
    // LogCat tag
    private static String TAG = PreferenceUtils.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    public PreferenceUtils(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(Constants.PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setEnglish() {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());
        editor.putString("lan", "en");
        editor.apply();

    }

    public void setArabic() {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());
        editor.putString("lan", "ar");
        editor.apply();

    }


    /**
     * here to get the user id  from the shared prefrence
     *
     * @return the user id
     */
    public String getLanguage() {
        return pref.getString("lan", "en");
    }

    public String getLanguage__first() {
        return pref.getString("lan", "null");
    }


    //todo here to save the user id

    public void setPersonId(int numSub) {

        editor.putInt(Constants.ID_CODE, numSub);
        // commit changes
        editor.commit();

    }

    public int getpersonID() {
        return pref.getInt(Constants.ID_CODE, -1);
    }

    public void setUserToken(String token) {

        editor.putString(Constants.TOKEN, token);
        // commit changes
        editor.commit();

    }

    public String getUserToken() {
        return pref.getString(Constants.TOKEN, "0");
    }


}
