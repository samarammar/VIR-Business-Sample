package com.accuragroup.eg.VirAdmin.language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ApplyConfigurationsChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageHelper.applyLanguage(this);
//    setContentView(R.layout.activity_framelayout);
    }
}