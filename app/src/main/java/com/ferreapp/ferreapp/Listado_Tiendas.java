package com.ferreapp.ferreapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Listado_Tiendas extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_tiendas);

        Button btnProfile = (Button) findViewById(R.id.btnProfile);
        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        Button btnPrivacy = (Button) findViewById(R.id.btnPrivacy);

        View panelProfile = findViewById(R.id.panelProfile);
        panelProfile.setVisibility(View.GONE);

        View panelSettings = findViewById(R.id.panelSettings);
        panelSettings.setVisibility(View.GONE);

        View panelPrivacy = findViewById(R.id.panelPrivacy);
        panelPrivacy.setVisibility(View.GONE);

        btnProfile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View panelProfile = findViewById(R.id.panelProfile);
                panelProfile.setVisibility(View.VISIBLE);

                View panelSettings = findViewById(R.id.panelSettings);
                panelSettings.setVisibility(View.GONE);

                View panelPrivacy = findViewById(R.id.panelPrivacy);
                panelPrivacy.setVisibility(View.GONE);

            }
        });

        btnSettings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View panelProfile = findViewById(R.id.panelProfile);
                panelProfile.setVisibility(View.GONE);

                View panelSettings = findViewById(R.id.panelSettings);
                panelSettings.setVisibility(View.VISIBLE);

                View panelPrivacy = findViewById(R.id.panelPrivacy);
                panelPrivacy.setVisibility(View.GONE);

            }
        });

        btnPrivacy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View panelProfile = findViewById(R.id.panelProfile);
                panelProfile.setVisibility(View.GONE);

                View panelSettings = findViewById(R.id.panelSettings);
                panelSettings.setVisibility(View.GONE);

                View panelPrivacy = findViewById(R.id.panelPrivacy);
                panelPrivacy.setVisibility(View.VISIBLE);

            }
        });

    }

}