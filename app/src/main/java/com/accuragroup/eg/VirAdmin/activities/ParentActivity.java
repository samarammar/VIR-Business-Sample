package com.accuragroup.eg.VirAdmin.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.language.LanguageHelper;
import com.accuragroup.eg.VirAdmin.language.Languages;
import com.accuragroup.eg.VirAdmin.utils.DialogUtils;
import com.accuragroup.eg.VirAdmin.utils.Utils;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ParentActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageButton ibCart;
    // used to hold connection handlers that should be cancelled when destroyed
    protected AppCompatActivity activity;
    protected ProgressDialog progressDialog;
    private FrameLayout rootView;
    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private int menuId;
    private boolean enableBack;
    TextView tvCart;
    private int iconResId;
    private String toolbarTitle;
    private ImageButton ibSearch;

    public int Compared = 0;
    public int Liked = 0;
    public int Favourited = 0;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activity = this;
        rootView = (FrameLayout) findViewById(android.R.id.content);

        if (Utils.getCachedString(this, Const.Language, "").equals("ar")) {

            LanguageHelper.changeLanguage(this, Languages.ARABIC);
            LanguageHelper.applyLanguage(this);

        } else {

            LanguageHelper.changeLanguage(this, Languages.ENGLISH);
            LanguageHelper.applyLanguage(this);
        }



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // init the view_home_toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // check the view_home_toolbar
        if (toolbar != null) {
            // view_home_toolbar is available >> handle it
            setSupportActionBar(toolbar);

            toolbar.setTitle("");

            tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
            tvToolbarTitle.setText(getTitle());

            // check if enable back
            if (enableBack) {
                // set the suitable back icon
                if (iconResId != 0) {

                    toolbar.setNavigationIcon(iconResId);

                } else {

                    if (Utils.getAppLanguage().equals("ar")) {

                        toolbar.setNavigationIcon(R.drawable.ic_right_back);
                    } else {

                        toolbar.setNavigationIcon(R.drawable.ic_back);
                    }
                }
            } else if (iconResId != 0) {
                // set this icon
                toolbar.setNavigationIcon(iconResId);
            }
        }
    }

    public void logE(String msg) {
        Utils.logE(msg);
    }


    @Override
    protected void onDestroy() {
        // cancel requests if found

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {


    }

    protected void goCart() {

        // startActivity(new Intent(this, HomeActivity.class).putExtra(Const.Flag, Const.Cart));

    }

    public void showProgressDialog() {

            if (progressDialog != null) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            } else {
                progressDialog = DialogUtils.showProgressDialog(this, R.string.please_wait_dotted);
            }
    }

    public void hideProgressDialog() {
        try {
        if (progressDialog != null && this.progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        } catch (final IllegalArgumentException e) {
            Log.i("IllegalArgumentEx", String.valueOf(e));
        } catch (final Exception e) {
            Log.i("Exception", String.valueOf(e));
        } finally {
            this.progressDialog = null;
        }
    }



    public void hideCart() {
        if (ibCart != null) {

            ibCart.setVisibility(View.GONE);
        }

    }

    public void setCartIcon() {
        if (ibCart != null) {

            ibCart.setVisibility(View.VISIBLE);
        }
    }

    public void hideSearch() {

        if (ibSearch != null) {

            ibSearch.setVisibility(View.GONE);
        }
    }

    public void showSearch() {

        if (ibSearch != null) {

            ibSearch.setVisibility(View.VISIBLE);
        }
    }


    public int getResColor(int id) {
        return getResources().getColor(id);
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }

    public void hideKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void setTitle(CharSequence title) {
        toolbarTitle = title.toString();
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setText(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        toolbarTitle = getString(titleId);
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setText(titleId);
        } else {
            super.setTitle(titleId);
        }
    }

    public void hideToolbarTitle() {
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setVisibility(View.GONE);
        }
    }

    public void showToolbarTitle() {
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setVisibility(View.VISIBLE);
        }
    }

    public void createOptionsMenu(int menuId) {
        this.menuId = menuId;
        invalidateOptionsMenu();
    }

    public void removeOptionsMenu() {
        menuId = 0;
        invalidateOptionsMenu();
    }

    public void enableBackButton() {
        enableBack = true;
    }

    public void setToolbarIcon(int iconResId) {
        this.iconResId = iconResId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuId != 0) {
            getMenuInflater().inflate(menuId, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && enableBack) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public boolean hasToolbar() {
        return toolbar != null;
    }
}