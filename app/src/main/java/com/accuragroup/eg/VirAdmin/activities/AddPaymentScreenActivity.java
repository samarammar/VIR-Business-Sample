package com.accuragroup.eg.VirAdmin.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.dialogs.PaymentMethodsDialog;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.accuragroup.eg.VirAdmin.R.id.tv_paymentPlan;

public class AddPaymentScreenActivity extends ParentActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_payment_method)
    EditText etPaymentMethod;
    @BindView(R.id.et_payment_plan)
    EditText etPaymentPlan;
    @BindView(tv_paymentPlan)
    TextView tvPaymentPlan;
    @BindView(R.id.tv_paymentDuration)
    TextView tvPaymentDuration;
    @BindView(R.id.tv_ExpirationText)
    TextView tvExpirationText;
    @BindView(R.id.tv_EpirationTime)
    TextView tvEpirationTime;
    @BindView(R.id.tv_paymentPrice)
    TextView tvPaymentPrice;
    @BindView(R.id.btn_confirmPayment)
    Button btnConfirmPayment;
    @BindView(R.id.lay_renewPlan)
    LinearLayout layRenewPlan;
    @BindView(R.id.tv_paymentSettings)
    TextView tvPaymentSettings;
    @BindView(R.id.btn_paymentCode)
    Button btnPaymentCode;
    @BindView(R.id.footer)
    RelativeLayout footer;
    @BindView(R.id.lay_main_addpay)
    RelativeLayout layMainAddpay;

    PaymentMethodsDialog paymentMethodsDialog;
    android.app.FragmentManager fragmentManager;
    int methodId;
    SimpleDateFormat sdf;
    Date todayDate = null;
    Date date=null;
    Date expiredate=null;
    private int infofees, discount,ownerId;
    private int amount = 0,duration=0;
    private String paymentPlan,PaymentExpire,customDate,ExpireDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_screen);
        ButterKnife.bind(this);
        getDataFromIntent();
        initViews();

        getPaymentInfo();
        setTitle(getString(R.string.review_plan));
        enableBackButton();

    }

    public void initViews() {
        sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        ownerId = Integer.parseInt(Utils.getCachedString(this, "USERID", ""));

        switch (paymentPlan)
        {
            case "T":
                tvPaymentPlan.setText(getString(R.string._days_trial));
                tvPaymentPlan.setTextColor(Color.parseColor("#FF0000"));
                tvEpirationTime.setText(PaymentExpire);
                break;
            case "Std":
                tvPaymentPlan.setText(getString(R.string.standard));
                tvEpirationTime.setText(PaymentExpire);
                break;
            case "Slvr":
                tvPaymentPlan.setText(getString(R.string.silver));
                tvEpirationTime.setText(PaymentExpire);
                break;
            case "G":
                tvPaymentPlan.setText(getString(R.string.gold));
                tvEpirationTime.setText(PaymentExpire);
                break;
            case "E":
                tvPaymentPlan.setText(getString(R.string.expired));
                tvExpirationText.setVisibility(View.GONE);
                tvEpirationTime.setText(R.string.subscribe_to_manage_account);
                break;

        }
//

        paymentMethodsDialog=new PaymentMethodsDialog();
        fragmentManager = getFragmentManager();

        paymentMethodsDialog.setOnDialogClickedListener(new PaymentMethodsDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name, String setting) {
                footer.setVisibility(View.VISIBLE);
                methodId = position;
                etPaymentMethod.setText(name);
                if (methodId==1|methodId==2||methodId==3) {
                    btnPaymentCode.setText(setting);
                    btnPaymentCode.setVisibility(View.VISIBLE);
                    tvPaymentSettings.setText(getString(R.string.this_your_code_please_use_it_to_renew_your_plan_through_selected_payment_method));
                }else if(methodId==8){
                    btnPaymentCode.setVisibility(View.VISIBLE);
                    btnPaymentCode.setText(R.string.pay_now);
                    tvPaymentSettings.setText(R.string.plz_press_the_next_btn);
                    btnConfirmPayment.setVisibility(View.GONE);
                }else {
                    btnPaymentCode.setVisibility(View.GONE);
                    tvPaymentSettings.setText(setting);
                }
            }
        });
    }

    @OnClick({R.id.et_payment_method, R.id.et_payment_plan, R.id.btn_confirmPayment,R.id.btn_paymentCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_payment_method:

                paymentMethodsDialog.show(fragmentManager,"");

                break;
            case R.id.et_payment_plan:
                choosePaymentPlan();
                break;
            case R.id.btn_paymentCode:
                if (methodId==8&&duration!=0&&amount!=0){

                    Intent intent = new Intent(this,PaymentWebViewActivity.class);
                    intent.putExtra("ownerId",ownerId);
                    intent.putExtra("methodId",methodId);
                    intent.putExtra("start_date",customDate);
                    intent.putExtra("duration",duration);
                    intent.putExtra("amount",amount);
                    startActivity(intent);

                }else if((methodId==8&&duration==0&&amount==0)) {
                    Utils.showShortToast(AddPaymentScreenActivity.this, getString(R.string.payment_period));
                    tvPaymentDuration.requestFocus();
                }

                break;
            case R.id.btn_confirmPayment:
                Log.i("customDate",customDate);
                if (Valid(methodId, duration)) {
                    addPayment(ownerId,methodId,amount,customDate,duration);
                    break;
                }
        }
    }

    public void checkDate() throws ParseException {
        Calendar calendar;
        calendar = Calendar.getInstance();
        String TodayDate = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);



        date=sdf.parse(TodayDate);
        expiredate=sdf.parse(ExpireDate);
        if (date.after(sdf.parse(ExpireDate))) {
            if (calendar.get(Calendar.DAY_OF_MONTH)>=1&&calendar.get(Calendar.DAY_OF_MONTH)<15){
                customDate = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + 1 ;
                todayDate = sdf.parse(customDate);
                Log.i("date1", sdf.format(todayDate));

            }else {
                customDate = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + 15 ;

                todayDate = sdf.parse(customDate);
                Log.i("date2", sdf.format(todayDate));
            }
        }else {
            Calendar cal = Calendar.getInstance();
            int days = expiredate.getDate();
            cal.setTime(expiredate);

            todayDate=cal.getTime();
            if (days>=1&&days<15){
                todayDate.setDate(1);
                customDate=sdf.format(todayDate);
            }else {
                todayDate.setDate(15);
                customDate=sdf.format(todayDate);
            }
        }
    }

    private void addPayment(int ownerId, int methodId, int amount, String fromDate, int duration) {
        JsonObject data = new JsonObject();
        data.addProperty("ownerId", ownerId);
        data.addProperty("methodId", methodId);
        data.addProperty("amount", amount);
        data.addProperty("duration", duration);
        data.addProperty("fromDate", fromDate);
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);

        showProgressDialog();
        Ion.with(AddPaymentScreenActivity.this)
                .load(ServiceApi.Service.addPayment.getBaseService())
                .setJsonObjectBody(data)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if (e != null) {
                    showProgressDialog();
                    Utils.showShortToast(AddPaymentScreenActivity.this, getString(R.string.connectionFieldTryAgain));
                    e.printStackTrace();
                    hideProgressDialog();
                } else {
                    String s = result.get("status").toString();
                    Log.i("data", s);

                    if (s.equals("200")) {
                        String m = result.get("message").toString();
                        finish();
                        startActivity(new Intent(AddPaymentScreenActivity.this,PaymentScreenActivity.class));
                        hideProgressDialog();

                    } else {
                        hideProgressDialog();
                        Utils.showShortToast(AddPaymentScreenActivity.this,  getString(R.string.faild));
                    }
                }
            }
        });
    }


    boolean isValid = false;

    private boolean Valid(int methodid, int duration) {

        if (methodid == 0) {
            Utils.showShortToast(AddPaymentScreenActivity.this, getString(R.string.choose_method));
            tvPaymentPlan.requestFocus();
        } else if (duration == 0) {
            Utils.showShortToast(AddPaymentScreenActivity.this, getString(R.string.payment_period));
            tvPaymentDuration.requestFocus();
        } else {
            isValid = true;
        }
        return isValid;


    }

    public void choosePaymentPlan(){
        android.app.AlertDialog dialog;

        final CharSequence[] items = { getString(R.string.m3onths), getString(R.string.m6onths), getString(R.string.year)};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle( getString(R.string.choose_payment_period));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                switch (pos) {
                    case 0:
                    {
                        try {
                            checkDate();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        duration = 3;
                        amount = 3 * infofees;
                        Log.i("amount3", String.valueOf(amount));
                        tvPaymentPrice.setText(String.valueOf(amount) +" "+ getString(R.string.egp));
                        java.util.Date dateMonth = new Date();
                        Calendar cal = Calendar.getInstance();
                        int monthss= todayDate.getMonth();
                        Log.i("Date", String.valueOf(monthss));
                        cal.set(Calendar.MONTH, monthss+3);
                        dateMonth = cal.getTime();
                        todayDate.setMonth( monthss+3);
                        Log.i("Months",sdf.format(todayDate));
                        tvEpirationTime.setText(sdf.format(todayDate));
                        tvPaymentDuration.setText(items[pos]);
                        etPaymentPlan.setText(items[pos]);
                        tvPaymentPlan.setText(getString(R.string.standard));
                        layRenewPlan.setVisibility(View.VISIBLE);
                        if (methodId==8){
                            btnConfirmPayment.setVisibility(View.GONE);
                        }else{
                            btnConfirmPayment.setVisibility(View.VISIBLE);
                        }

                        tvPaymentPlan.setTextColor(Color.parseColor("#2196F3"));

                    }break;
                    case 1:
                    {
                        try {
                            checkDate();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        duration = 6;
                        amount = 6 * infofees;
                        Log.i("amount6", String.valueOf(amount));
                        tvPaymentPrice.setText(amount +" "+ getString(R.string.egp));
                        java.util.Date dateMonth = new Date();
                        Calendar cal = Calendar.getInstance();
                        int monthss= todayDate.getMonth();
                        cal.set(Calendar.MONTH, monthss+6);
                        dateMonth = cal.getTime();

                        todayDate.setMonth( monthss+6);
                        Log.i("Months",sdf.format(todayDate));
                        tvEpirationTime.setText(sdf.format(todayDate));
                        tvPaymentDuration.setText(items[pos]);
                        etPaymentPlan.setText(items[pos]);
                        tvPaymentPlan.setText(R.string.silver);
                        layRenewPlan.setVisibility(View.VISIBLE);

                        if (methodId==8){
                            btnConfirmPayment.setVisibility(View.GONE);
                        }else{
                            btnConfirmPayment.setVisibility(View.VISIBLE);
                        }
                        tvPaymentPlan.setTextColor(Color.parseColor("#2196F3"));

                    }break;
                    case 2:
                    {
                        try {
                            checkDate();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        duration = 12;
                        int fees = 12 * infofees;
                        float percent=(float)discount / 100;
                        int discountfees = (int) (fees * percent);
                        amount = fees - discountfees;
                        tvPaymentPrice.setText(String.valueOf(amount) +" "+ getString(R.string.egp));
                        java.util.Date dateMonth = new Date();
                        Calendar cal = Calendar.getInstance();
                        int monthss= todayDate.getMonth();
                        cal.set(Calendar.MONTH, monthss+12);
                        dateMonth = cal.getTime();
                        todayDate.setMonth( monthss+12);
                        Log.i("Months",sdf.format(todayDate));
                        tvEpirationTime.setText(sdf.format(todayDate));
                        tvPaymentDuration.setText(items[pos]);
                        etPaymentPlan.setText(getString(R.string.year));
                        tvPaymentPlan.setText(getString(R.string.gold));
                        layRenewPlan.setVisibility(View.VISIBLE);
                        if (methodId==8){
                            btnConfirmPayment.setVisibility(View.GONE);
                        }else{
                            btnConfirmPayment.setVisibility(View.VISIBLE);
                        }
                        tvPaymentPlan.setTextColor(Color.parseColor("#2196F3"));

                    }break;
                }
            }});
        dialog=builder.create();
        dialog.show();
    }

    public void getDataFromIntent(){
        Intent intent=getIntent();
        paymentPlan = intent.getStringExtra(Const.PaymentPlan);
        PaymentExpire = intent.getStringExtra(Const.PaymentExpiration);
        ExpireDate=intent.getStringExtra(Const.ExpirationDate);
    }


    private void getPaymentInfo() {
        JsonObject data = new JsonObject();
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);


        showProgressDialog();
        Ion.with(AddPaymentScreenActivity.this)
                .load(ServiceApi.Service.getPaymentInfo.getBaseService())
                .setJsonObjectBody(data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String s = result.get("status").toString();
                        if (e != null) {
                            hideProgressDialog();
                            Utils.showShortToast(AddPaymentScreenActivity.this, getString(R.string.connectionFieldTryAgain));
                            e.printStackTrace();
                        } else if (s.equals("200")) {
                            JsonObject info = result.getAsJsonObject("return");
                            infofees = info.get("monthlyFees").getAsInt();
                            discount = info.get("annualDiscount").getAsInt();
                            hideProgressDialog();
                        }

                    }
                });
    }



}
