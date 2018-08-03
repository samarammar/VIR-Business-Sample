package com.accuragroup.eg.VirAdmin;

import android.app.Application;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.accuragroup.eg.VirAdmin.models.Entities.DaoMaster;
import com.accuragroup.eg.VirAdmin.models.Entities.DaoSession;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;


import org.greenrobot.greendao.database.Database;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Apex on 7/9/2017.
 */

public class VirAdmin extends Application {

    private DaoSession daoSession;
    public static final boolean ENCRYPTED = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "vir-db-encrypted" : getString(R.string.appName));
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        MultiDex.install(this);


        // override default font
        Utils.overrideFont(this, "MONOSPACE", "fonts/app_font.ttf");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensans-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

//        if (FirebaseApp.getApps(this).isEmpty()){
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
