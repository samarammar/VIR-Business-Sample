package com.accuragroup.eg.VirAdmin;

import android.util.Log;

import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.koushikdutta.async.Util;

/**
 * Created by Apex on 3/21/2018.
 */

public class MyFireBaseInstancIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String recenttoken= FirebaseInstanceId.getInstance().getToken();
        Utils.cacheString(getApplicationContext(),Const.REG_TOKEN,recenttoken);
        Log.i(Const.REG_TOKEN,recenttoken);
    }
}
