package com.accuragroup.eg.VirAdmin.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;

import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.accuragroup.eg.VirAdmin.R.drawable.default_avatar;


/**
 * Created by karam on 6/13/17.
 */

public class NavigationActivity extends ParentActivity {

    static int DRAWER_GRAVITY = Gravity.START;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton ibNavigation;
    private String UserId;
    android.support.v7.widget.Toolbar toolbar;
    private CircleImageView ivUserImage;
    private TextView tvUserName, tvSignOut;
    private View header;
    private String userType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_navigation);
        initViews();

        setupNavigation();

        String key = Utils.getCachedString(NavigationActivity.this, "via", "");
        if (key.equals("facebook")) {
            setUserFacebookDataIntoNavigation();

        } else if (key.equals("default")) {
           setUserSignupDataIntoNavigation();
        }


        ibNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuIcon();
            }
        });


    }

    private void initViews() {
        UserId = Utils.getCachedString(this, "USERID", "");
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        header = navigationView.getHeaderView(0);
        ivUserImage = (CircleImageView) header.findViewById(R.id.iv_userImage);
        tvUserName = (TextView) header.findViewById(R.id.tv_userName);
        ibNavigation = (ImageButton) findViewById(R.id.ib_navigation);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tvSignOut = (TextView) header.findViewById(R.id.tv_signOut);

        userType = Utils.getCachedString(NavigationActivity.this, "userType", "");

        if (userType.equals(Const.USER_OWNER)) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_menu_business);
        } else if (userType.equals(Const.USER_Emp)) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_menu_employee);
        } else {
        }
        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);

                View view = LayoutInflater.from(NavigationActivity.this).inflate(R.layout.dialog_sign_out, null);

                TextView title = (TextView) view.findViewById(R.id.title);

                title.setText(R.string.sign_out);


                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginManager.getInstance().logOut();
                        Utils.clearAll(NavigationActivity.this);


                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK));

                        // finishAffinity();


                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(NavigationActivity.this, "Never Mind!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();
            }
        });


    }


    private void setUserSignupDataIntoNavigation() {
        String imageUrl=null;
        String profileName="";



        if (userType.equals(Const.USER_Emp)){
            profileName = Utils.getCachedString(NavigationActivity.this, "display_name", "");}
        else if (userType.equals(Const.USER_OWNER)){
            profileName = Utils.getCachedString(NavigationActivity.this, "shop_name", "");}

        tvUserName.setText(profileName);

        if (userType.equals(Const.USER_Emp)){
            imageUrl= Utils.getCachedString(NavigationActivity.this, "image", "");}
        else if (userType.equals(Const.USER_OWNER)){
            imageUrl = Utils.getCachedString(NavigationActivity.this, "shop_image", "");}

        if (imageUrl == null||imageUrl.isEmpty()) {
            ivUserImage.setImageResource(R.drawable.default_avatar);
        } else {
            Picasso.with(NavigationActivity.this).load(imageUrl).error(getApplicationContext().getResources().getDrawable(default_avatar)).into(ivUserImage);
        }
    }
    private void setUserFacebookDataIntoNavigation() {

        String profileName = Utils.getCachedString(NavigationActivity.this, "shop_name", "");
        String profileImageLink = Utils.getCachedString(NavigationActivity.this, "image", null);
        tvUserName.setText(profileName);
        if (profileImageLink == null||profileImageLink.isEmpty()) {
            ivUserImage.setImageResource(R.drawable.default_avatar);
        } else {
            Picasso.with(NavigationActivity.this).load(profileImageLink).error(getApplicationContext().getResources().getDrawable(default_avatar)).into(ivUserImage);
        }
    }

    private void setupNavigation() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.payments:
                        startActivity(new Intent(getApplicationContext(), PaymentScreenActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;


                    ///Employee

                    case R.id.Mystore_Business:
                        startActivity(new Intent(getApplicationContext(), MyStoreActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;


                }

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onMenuIcon() {

        if (drawerLayout.isDrawerOpen(DRAWER_GRAVITY)) {
            closeMenuDrawer();
        } else {
            drawerLayout.openDrawer(DRAWER_GRAVITY);
        }
    }

    public void closeMenuDrawer() {
        drawerLayout.closeDrawer(DRAWER_GRAVITY);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(DRAWER_GRAVITY)) {
            closeMenuDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void hideItems() {
        Menu nav_Menu = navigationView.getMenu();

    }
}
