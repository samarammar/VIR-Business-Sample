package com.accuragroup.eg.VirAdmin.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.models.Request.PaymentRequest;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentScreenActivity extends NavigationActivity {

    public String duration, formatted_to_date, payment_method_, payment_icon, formatted_date, payment_expiration_date,paymentID;
    public int method_id, amount, payment_is_paid, is_free_duration;
    String customDate;
    SimpleDateFormat sdf;
    Date currentDate = null;

    public int ownerId;
    @BindView(R.id.tv_paymentMethodIntro)
    TextView tvPaymentMethodIntro;
    @BindView(R.id.tv_Standard)
    TextView tvStandard;
    @BindView(R.id.tv_Expiration)
    TextView tvExpiration;
    @BindView(R.id.tv_EpirationDate)
    TextView tvEpirationDate;
    @BindView(R.id.btn_renew_payment)
    Button btnRenewPayment;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.lay_method)
    LinearLayout layMethod;

    String paymentPlan, expireDate;
    @BindView(R.id.tv_paymentDoneText)
    TextView tvPaymentDoneText;
    @BindView(R.id.iv_paymentIcon)
    ImageView ivPaymentIcon;
    @BindView(R.id.footer)
    RelativeLayout footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_payment_screen, null, false);
        drawerLayout.addView(contentView, 1);
        setTitle(getString(R.string.payment));
        ButterKnife.bind(this);

        initView();
        getLastPayment();
    }


    public void initView() {
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        ownerId = Integer.parseInt(Utils.getCachedString(PaymentScreenActivity.this, "USERID", ""));
        Log.i("ownerid", String.valueOf(ownerId));

        Calendar calendar;
        calendar = Calendar.getInstance();
        String customDate = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        try {
            currentDate = sdf.parse(customDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void getLastPayment() {
        PaymentRequest params = new PaymentRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setLangu(Utils.getCachedString(this, Const.Language, ""));
        params.setOwnerId(ownerId);


        Ion.with(this)
                .load(ServiceApi.Service.getLastPayment.getBaseService())
                .setJsonPojoBody(params)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if (e != null) {
                    Utils.showShortToast(PaymentScreenActivity.this, getString(R.string.connectionFieldTryAgain));

                } else {
                    if (this != null) {
                        progress.setVisibility(View.GONE);
                        layMethod.setVisibility(View.VISIBLE);
                        JsonObject payment = result.getAsJsonObject("return");
                        paymentID=payment.get("ID").getAsString();
                        Utils.cacheString(PaymentScreenActivity.this,"ID",paymentID);
                        duration = payment.get("period").getAsString();
                        formatted_to_date = payment.get("formatted_to_date").getAsString();
                        formatted_date = payment.get("formatted_date").getAsString();
                        payment_expiration_date = payment.get("payment_expiration_date").getAsString();
                        method_id = payment.get("method_id").getAsInt();
                        payment_method_ = payment.get("payment_method_").getAsString();
                        payment_icon = payment.get("payment_icon").toString();
                        amount = payment.get("amount").getAsInt();
                        payment_is_paid = payment.get("payment_is_paid").getAsInt();
                        is_free_duration = payment.get("is_free_duration").getAsInt();
                        if (payment_is_paid == 1) {
                            setData();
                        } else {
                            setPendingData();
                        }

                    }
                }
            }
        });
    }


    public void setPendingData() {
        btnRenewPayment.setVisibility(View.GONE);
        footer.setVisibility(View.VISIBLE);
        Picasso.with(this).load(payment_icon.substring(1,payment_icon.length() - 1)).into(ivPaymentIcon);
        tvEpirationDate.setText(formatted_to_date);
        tvExpiration.setText(R.string.expired_on);
        if (duration.equals("3")) {
            tvStandard.setText(getString(R.string.standard));
            tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
        } else if (duration.equals("6")) {
            tvStandard.setText(R.string.silver);
            tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
        } else if (duration.equals("12")) {
            tvStandard.setText(getString(R.string.gold));
            tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
        }
    }


    public void setData() {
        if (duration.equals("1") && is_free_duration == 1) {


            tvStandard.setText(getString(R.string._days_trial));
            tvPaymentMethodIntro.setText(getString(R.string.you_are_enjoying));
            tvEpirationDate.setText(formatted_to_date);
            btnRenewPayment.setText(getString(R.string.subscribe));
            tvStandard.setTextColor(Color.parseColor("#FF0000"));
            expireDate = formatted_to_date;
            paymentPlan = "T";

        } else if (duration.equals("3")) {

            Date strDate = null;
            try {

                strDate = sdf.parse(formatted_to_date);
                if (currentDate.after(strDate)) {
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    int days = strDate.getDate();
                    cal.set(Calendar.DAY_OF_MONTH, days + 7);
                    date = cal.getTime();
                    if (currentDate.after(sdf.parse(sdf.format(date)))) {
                        paymentPlan = "E";
                        tvStandard.setText(getString(R.string.expired));
                        tvPaymentMethodIntro.setText(getString(R.string.your_account_is));
                        btnRenewPayment.setText(getString(R.string.subscribe));
                        tvEpirationDate.setVisibility(View.GONE);
                        tvExpiration.setText(R.string.subscribe_to_manage_account);
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        expireDate = sdf.format(date);
                    } else {
                        tvStandard.setText(getString(R.string._days_trial));
                        tvPaymentMethodIntro.setText(getString(R.string.you_are_enjoying));
                        tvEpirationDate.setText(sdf.format(date));
                        btnRenewPayment.setText(getString(R.string.subscribe));
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        paymentPlan = "T";
                        expireDate = sdf.format(date);
                    }
                } else {
                    Log.i("out of date", "false");
                    tvStandard.setText(getString(R.string.standard));
                    tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
                    tvEpirationDate.setText(formatted_to_date);
                    btnRenewPayment.setText(getString(R.string.renew));
                    paymentPlan = "Std";
                    expireDate = formatted_to_date;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (duration.equals("6")) {


            Date strDate = null;
            try {

                strDate = sdf.parse(formatted_to_date);
                if (currentDate.after(strDate)) {
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    int days = strDate.getDate();
                    cal.set(Calendar.DAY_OF_MONTH, days + 7);
                    date = cal.getTime();
                    if (currentDate.after(sdf.parse(sdf.format(date)))) {
                        tvStandard.setText(getString(R.string.expired));
                        paymentPlan = "E";
                        tvPaymentMethodIntro.setText(getString(R.string.your_account_is));
                        btnRenewPayment.setText(getString(R.string.subscribe));
                        tvEpirationDate.setVisibility(View.GONE);
                        tvExpiration.setText(R.string.subscribe_to_manage_account);
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        expireDate = sdf.format(date);
                    } else {
                        paymentPlan = "T";
                        tvStandard.setText(getString(R.string._days_trial));
                        tvPaymentMethodIntro.setText(getString(R.string.you_are_enjoying));
                        tvEpirationDate.setText(sdf.format(date));
                        btnRenewPayment.setText(getString(R.string.subscribe));
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        expireDate = sdf.format(date);
                    }

                } else {
                    Log.i("out of date", "false");
                    tvStandard.setText(R.string.silver);
                    tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
                    tvEpirationDate.setText(formatted_to_date);
                    btnRenewPayment.setText(getString(R.string.renew));
                    paymentPlan = "Slvr";
                    expireDate = formatted_to_date;


                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (duration.equals("12")) {


            Date strDate = null;
            try {

                strDate = sdf.parse(formatted_to_date);
                if (currentDate.after(strDate)) {
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    int days = strDate.getDate();
                    cal.set(Calendar.DAY_OF_MONTH, days + 7);
                    date = cal.getTime();
                    if (currentDate.after(sdf.parse(sdf.format(date)))) {
                        tvStandard.setText(getString(R.string.expired));
                        tvPaymentMethodIntro.setText(getString(R.string.your_account_is));
                        btnRenewPayment.setText(getString(R.string.subscribe));
                        tvEpirationDate.setVisibility(View.GONE);
                        tvExpiration.setText(R.string.subscribe_to_manage_account);
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        paymentPlan = "E";
                        expireDate = sdf.format(date);

                    } else {
                        tvStandard.setText(getString(R.string._days_trial));
                        tvPaymentMethodIntro.setText(getString(R.string.you_are_enjoying));
                        tvEpirationDate.setText(sdf.format(date));
                        tvStandard.setTextColor(Color.parseColor("#FF0000"));
                        paymentPlan = "T";
                        expireDate = sdf.format(date);
                    }

                } else {
                    Log.i("out of date", "false");
                    tvStandard.setText(getString(R.string.gold));
                    tvPaymentMethodIntro.setText(getString(R.string.your_current_plan));
                    tvEpirationDate.setText(formatted_to_date);
                    btnRenewPayment.setText(getString(R.string.renew));
                    paymentPlan = "G";
                    expireDate = formatted_to_date;


                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    @OnClick(R.id.btn_renew_payment)
    public void onViewClicked() {
        startActivity(new Intent(PaymentScreenActivity.this, AddPaymentScreenActivity.class)
                .putExtra(Const.PaymentPlan, paymentPlan).putExtra(Const.PaymentExpiration, expireDate)
                .putExtra(Const.ExpirationDate,payment_expiration_date)
        );
    }
}
