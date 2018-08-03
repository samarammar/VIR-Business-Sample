package com.accuragroup.eg.VirAdmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;

public class ProductPoliciesActivity extends ParentActivity {
    private Button btnAgree;
    private String Rules;
    private TextView tvtitle,tvrulel,tvrule2,tvrule7,tvrule8,tvrule9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_policies);
        enableBackButton();

        getdataFromIntent();
        if (Rules.equals("product")) {
            setTitle(getString(R.string.add_produt_rule));
        } else if(Rules.equals("offer")){
            setTitle(getString(R.string.add_offer_rule));
        }
        initViews();
          if(Rules.equals("offer")){
           tvtitle.setText(R.string.You_Have_to_read_these);
           tvrulel.setText(R.string._1__The_maximum_offer);
           tvrule2.setText(R.string._2__The_maximum_offer);
           tvrule7.setText(R.string._use_person_names);
           tvrule8.setText(R.string._8__The_shop_has_the_commitment);
           tvrule9.setText(R.string._9__VIR_Administration_has_the_full);
        }

    }



    private void initViews(){
        btnAgree=(Button)findViewById(R.id.btn_agree);
        tvtitle=(TextView) findViewById(R.id.tv_rulesTitles);
        tvrulel=(TextView)findViewById(R.id.tv_rule1);
        tvrule2=(TextView)findViewById(R.id.tv_rule2);
        tvrule7=(TextView)findViewById(R.id.tv_rule7);
        tvrule8=(TextView)findViewById(R.id.tv_rule8);
        tvrule9=(TextView)findViewById(R.id.tv_rule9);

        btnAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_agree:
                if(Rules.equals("product")) {
                    startActivity(new Intent(ProductPoliciesActivity.this, AddProductActivity.class));
                    finish();
                }
                break;
        }
    }

    private void getdataFromIntent(){
        Intent intent=getIntent();
        Rules=intent.getStringExtra("Rules");
        Log.i("Rules",Rules);
    }

}
