package com.accuragroup.eg.VirAdmin.language;

import java.util.Locale;

/**
 * all languages are defined here.
 * to add a new language, just add it here.
 */
public enum Languages {
    ARABIC("ar"), ENGLISH("en");
    private final String languageCode;
    private final String languageTitle;

    Languages(String code) {
        this.languageCode = code;
        Locale locale = new Locale(code);
        languageTitle = locale.getDisplayLanguage(locale);
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageTitle() {
        return languageTitle;
    }
}