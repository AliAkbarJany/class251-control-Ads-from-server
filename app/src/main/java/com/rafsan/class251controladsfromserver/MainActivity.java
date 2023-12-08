package com.rafsan.class251controladsfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    AdView mAdView;
    ProgressBar  progressBar;

    Button button;

    public static boolean SHOW_AD = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        progressBar = findViewById(R.id.progressBar);

        button = findViewById(R.id.button);


    //===========Banner Ad=====================

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


    //==================== Implement Banner Ad===========================
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    //=========================== Volley ============================
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://ali71.000webhostapp.com/apps/hello.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://ali71.000webhostapp.com/apps/hello.php" ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.contains("ShowAD")){

                            SHOW_AD = true;

                            progressBar.setVisibility(View.GONE);

                            mAdView.setVisibility(View.VISIBLE);
                            AdRequest adRequest = new AdRequest.Builder().build();
                            mAdView.loadAd(adRequest);

                        }
                        else {

                            SHOW_AD =false;

                            mAdView.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

//=========================== Volley end ============================


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }
}