package com.accuragroup.eg.VirAdmin.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class PaymentWebViewActivity extends ParentActivity {
    WebView webView;
    String ownerId,methodId,customDate,duration,amount,link,base64,sentData,paymentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);
        getdatafromBundle();
        initViews();
    }

    public void initViews(){
        class MyJavaScriptInterface
        {
            @JavascriptInterface
            @SuppressWarnings("unused")
            public void processHTML(String html)
            {
                Log.i("html",html);
                if (html.contains("vir_status=1")){
                    finish();
                    Intent intent = new Intent(PaymentWebViewActivity.this,PaymentResponseActivity.class);
                    intent.putExtra("status",1);
                    startActivity(intent);

                }else if (html.contains("vir_status=0")){
                    finish();
                    Intent intent = new Intent(PaymentWebViewActivity.this,PaymentResponseActivity.class);
                    intent.putExtra("status",0);
                    startActivity(intent);
                }
            }
        }
        webView= (WebView) findViewById(R.id.wv_payment);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        sentData=ownerId+"#"+methodId+"#"+customDate+"#"+duration+"#"+amount+"#"+paymentID;
        byte[] data = new byte[0];
        try {
            data = sentData.getBytes("UTF-8");
            base64 = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        link="http://vir-eg.net/payment-handling/?payment_info="+base64;
        Log.i("link",link);


        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {

                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });

        webView.loadUrl(link);

    }



    public void getdatafromBundle(){
        Intent intent=getIntent();
        paymentID= Utils.getCachedString(this,"ID","");
        ownerId= String.valueOf(intent.getIntExtra("ownerId",0));
        methodId= String.valueOf(intent.getIntExtra("methodId",0));
        customDate=intent.getStringExtra("start_date");
        duration= String.valueOf(intent.getIntExtra("duration",0));
        amount= String.valueOf(intent.getIntExtra("amount",0));

    }
}

