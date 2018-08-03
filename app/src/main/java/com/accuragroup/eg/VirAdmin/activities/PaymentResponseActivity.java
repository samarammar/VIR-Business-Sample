package com.accuragroup.eg.VirAdmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentResponseActivity extends ParentActivity {

    @BindView(R.id.iv_success)
    ImageView ivSuccess;
    @BindView(R.id.tv_success_Label)
    TextView tvSuccessLabel;
    @BindView(R.id.tv_success_Text)
    TextView tvSuccessText;
    @BindView(R.id.btn_finishPayment)
    Button btnFinishPayment;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_response);
        ButterKnife.bind(this);
        getDataFromBundle();
        setData();
    }

    public void getDataFromBundle(){
        Intent intent=getIntent();
        status= intent.getIntExtra("status",0);
    }

    public void setData(){
        if (status==0){
            setTitle(getString(R.string.wrong));
            ivSuccess.setImageResource(R.drawable.noun_1152961_cc);
            tvSuccessLabel.setText(R.string.something_went_wrong);
            tvSuccessText.setText(R.string.we_are_sorry_try_again);
            btnFinishPayment.setText(R.string.try_again);
        }else {
            setTitle(getString(R.string.thanks));
            ivSuccess.setImageResource(R.drawable.noun_1656248_cc);
            tvSuccessLabel.setText(R.string.thanks_your_payment_is_done);
            tvSuccessText.setText(R.string.we_are_glad_you_joined_vir_family_we_hope_you_enjoy_with_us_vir_works_hard_to_make_your_business_successful);
            btnFinishPayment.setText(R.string.go_to_your_account_now);
        }
    }

    @OnClick(R.id.btn_finishPayment)
    public void onViewClicked() {
        if (status==0){
            finish();
            startActivity(new Intent(PaymentResponseActivity.this,PaymentScreenActivity.class));
        }else {
            finish();
            startActivity(new Intent(PaymentResponseActivity.this,DashBoardActivity.class));
        }
    }
}
