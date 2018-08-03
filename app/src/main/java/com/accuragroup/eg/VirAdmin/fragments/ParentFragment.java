package com.accuragroup.eg.VirAdmin.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.activities.ParentActivity;
import com.accuragroup.eg.VirAdmin.utils.DialogUtils;
import com.accuragroup.eg.VirAdmin.utils.Utils;


public class ParentFragment extends Fragment implements View.OnClickListener {
    // used to hold connection handlers that should be cancelled when destroyed


    protected ParentActivity activity;
    protected View rootView;
    TextView tvCart;
    protected ProgressDialog progressDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (ParentActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activity.hasToolbar()) {
            setHasOptionsMenu(true);
        }
    }

    public View findViewById(int id) {
        if (rootView != null) {
            return rootView.findViewById(id);
        } else {
            return null;
        }
    }

    public int getResColor(int id) {
        return getResources().getColor(id);
    }

    public void logE(String msg) {
        Utils.logE(msg);
    }

    @Override
    public void onClick(View v) {
    }

    public void loadFragment(int container, Fragment fragment) {
        loadFragment(container, fragment, 0, 0);
    }

    public void loadFragment(int container, Fragment fragment, int enterAnim, int exitAnim) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        if (enterAnim != 0 && exitAnim != 0) {
            ft.setCustomAnimations(enterAnim, exitAnim);
        }
        ft.replace(container, fragment);
        ft.commitAllowingStateLoss();
    }

    public void showProgressDialog() {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            progressDialog = DialogUtils.showProgressDialog(activity, R.string.please_wait_dotted);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onDestroy() {
        // cancel requests if found

        super.onDestroy();
    }

    public void setTitle(CharSequence title) {
        if (activity != null) {
            activity.setTitle(title);
        }
    }

    public void setTitle(int titleId) {
        if (activity != null) {
            activity.setTitle(titleId);
        }
    }

    public void createOptionsMenu(int menuId) {
        if (activity != null) {
            activity.createOptionsMenu(menuId);
        }
    }

    public void removeOptionsMenu() {
        if (activity != null) {
            activity.removeOptionsMenu();
        }
    }

}
