package com.accuragroup.eg.VirAdmin.language;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {

    private static final String PREFS_NAME = "language.prefs";
    private static final String PREF_KEY_LANGUAGE = "language";
    private static String DEFAULT_LANG = Languages.ARABIC.name();

    public static void changeLanguage(Activity activity, Languages language) {
        activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putString(PREF_KEY_LANGUAGE, language.name()).apply();

    }

    public static Languages getLanguage(Context context) {
        return Languages.valueOf(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(PREF_KEY_LANGUAGE, DEFAULT_LANG));
    }

    public static void applyLanguage(Context context) {
        Languages language = Languages.valueOf(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(PREF_KEY_LANGUAGE, getDefaultLang(context)));
        Locale locale = new Locale(language.getLanguageCode());
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static int getLanguagePosition(Context context) {
        return getLanguage(context).ordinal();
    }

    public static String getDefaultLang(Context context) {

        if (Locale.getDefault().getLanguage().equals("ar"))
            DEFAULT_LANG = Languages.ARABIC.name();
        else
            DEFAULT_LANG = Languages.ENGLISH.name();
        return DEFAULT_LANG;
    }

}
