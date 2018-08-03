package com.accuragroup.eg.VirAdmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.accuragroup.eg.VirAdmin.R;

public class ExpirePrivilegeActivity extends ParentActivity {
    private Button Renew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expire_privilege);
        Renew =(Button)findViewById(R.id.btn_renew);

        enableBackButton();
        setTitle("VIR");

        Renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpirePrivilegeActivity.this, PaymentScreenActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
