package com.accuragroup.eg.VirAdmin.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.adapters.OwnerProductsAdapter;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;

import com.accuragroup.eg.VirAdmin.models.OwnerProductsModel;
import com.accuragroup.eg.VirAdmin.models.Responces.MyStoreProductResponce;
import com.accuragroup.eg.VirAdmin.models.Responces.SignupResponce;
import com.accuragroup.eg.VirAdmin.models.Responces.StoreProduct;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class MyStoreActivity extends NavigationActivity {
    private RecyclerView rvMyProducts;
    private FloatingActionButton fbAddProduct, fbAddProduct_empty;
    private LinearLayout error, main, expire, empty;
    private TextView tv_error;
    String UserId, userType;
    EditText etSearchProducts;
    String empId;

    private Button renew;
    private String productName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_my_store, null, false);
        drawerLayout.addView(contentView, 1);
        initViews();
        setTitle(R.string.my_store);
        etSearchProducts.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (Utils.isEmpty(etSearchProducts.getText())) {

                        productName = "";
                        if (Utils.hasConnection(MyStoreActivity.this)) {
                            getShopProducts(MyStoreActivity.this);
                        }else {
                            Utils.showShortToast(MyStoreActivity.this, getString(R.string.connectionFieldTryAgain));
                            showError();

                        }
                    } else {

                        productName = etSearchProducts.getText().toString();
                        Log.i("search", productName);
                        if (Utils.hasConnection(MyStoreActivity.this)) {
                            getShopProducts(MyStoreActivity.this);
                        }else {
                            Utils.showShortToast(MyStoreActivity.this, getString(R.string.connectionFieldTryAgain));
                            showError();
                        }

                    }
                }
                return false;
            }
        });

        rvMyProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Scroll Down
                    if (fbAddProduct.isShown()) {
                        fbAddProduct.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!fbAddProduct.isShown()) {
                        fbAddProduct.show();
                    }
                }
            }
        });
    }

    private void initViews() {
        rvMyProducts = (RecyclerView) findViewById(R.id.rv_my_store);
        UserId = Utils.getCachedString(MyStoreActivity.this, "USERID", "");
        userType = Utils.getCachedString(MyStoreActivity.this,"user_type", "");

        fbAddProduct = (FloatingActionButton) findViewById(R.id.fb_add_product_store);
        fbAddProduct_empty = (FloatingActionButton) findViewById(R.id.fb_add_product_store_empty);
        main = (LinearLayout) findViewById(R.id.lay_my_store_main);
        error = (LinearLayout) findViewById(R.id.lay_error_store);
        expire = (LinearLayout) findViewById(R.id.lay_expire_store);
        empty = (LinearLayout) findViewById(R.id.lay_prod_empty);
        renew = (Button) findViewById(R.id.btn_renew_store);

        userType = Utils.getCachedString(MyStoreActivity.this, Const.USER_TYPE, "");
        if (userType.equals(Const.USER_Emp)) {
            UserId = String.valueOf(Utils.getCachedInt(MyStoreActivity.this, Const.OWNER_ID, 0));
            empId=Utils.getCachedString(MyStoreActivity.this, "USERID", "");
        } else {
            UserId = Utils.getCachedString(MyStoreActivity.this, "USERID", "");
        }
        if (Utils.hasConnection(MyStoreActivity.this)) {
            getShopProducts(MyStoreActivity.this);
        }else {
            Utils.showShortToast(MyStoreActivity.this, getString(R.string.connectionFieldTryAgain));
            showError();
        }
        fbAddProduct.setOnClickListener(this);
        fbAddProduct_empty.setOnClickListener(this);
        renew.setOnClickListener(this);
        etSearchProducts = (EditText) findViewById(R.id.et_search_products);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fb_add_product_store:
            case R.id.fb_add_product_store_empty:
                Intent intent = new Intent(MyStoreActivity.this, ProductPoliciesActivity.class);
                intent.putExtra("Rules","product");
                startActivity(intent);
                break;
            case R.id.btn_renew_store:
                startActivity(new Intent(MyStoreActivity.this, PaymentScreenActivity.class));
                break;
        }
    }


    private void getShopProducts(final Context context) {
        JsonObject data = new JsonObject();
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("ownerId", Integer.valueOf(UserId.replaceAll("^\"|\"$", "")));
        if (userType.equals(Const.USER_Emp)) {
            data.addProperty("employeeId",Integer.valueOf(empId.replaceAll("^\"|\"$", "")));
        } else {
        }
        data.addProperty("page", 1);
        data.addProperty("perPage", 200);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);
        data.addProperty("keyword", productName);
        data.addProperty("langu", Utils.getCachedString(context, Const.Language, ""));

        showProgressDialog();
        Ion.with(context)
                .load(ServiceApi.Service.getOwnerProducts.getBaseService())
                .setJsonObjectBody(data)
                .as(new TypeToken<MyStoreProductResponce>() {
                })
                .setCallback(new FutureCallback<MyStoreProductResponce>() {
                    @Override
                    public void onCompleted(Exception e, MyStoreProductResponce result) {
                        if (e != null) {
                            Utils.showShortToast(MyStoreActivity.this, getString(R.string.connectionFieldTryAgain));
                            Utils.showShortToast(MyStoreActivity.this, String.valueOf(e));
                            Log.i("OBJ",String.valueOf(e));
                            hideProgressDialog();
                        } else {
                            if (result.getStatus() == 200) {
                                rvMyProducts.setVisibility(View.VISIBLE);
                                empty.setVisibility(View.GONE);
                                rvMyProducts.setLayoutManager(new LinearLayoutManager(MyStoreActivity.this));
                                rvMyProducts.setAdapter(new OwnerProductsAdapter(result.getReturn(), MyStoreActivity.this));
                                hideProgressDialog();
                            } else if(result.getStatus() == 407) {
                                showEmpty();
                                hideProgressDialog();
                            } else if(result.getStatus() == 410) {
                                showExpire();
                                hideProgressDialog();
                            } else if(result.getStatus() == 411){
                                logoutEmp();
                                hideProgressDialog();
                            }else {
                                showError();
                                hideProgressDialog();
                            }
                        }
                    }
                });
    }


    private void showError() {
        main.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    private void showExpire() {
        main.setVisibility(View.GONE);
        expire.setVisibility(View.VISIBLE);
    }

    private void showEmpty() {
        rvMyProducts.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void logoutEmp(){
        Utils.clearAll(MyStoreActivity.this);
        finishAffinity();
    }
}
