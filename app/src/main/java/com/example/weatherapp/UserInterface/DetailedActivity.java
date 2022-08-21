package com.example.weatherapp.UserInterface;

//s2026679 - Dean Mcardle
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    TextView detail_title, detail_desc, detail_link, detail_pubDate;
    FusedLocationProviderClient fusedLocationProviderClient;

    private String latTxt = "";
    private String longTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("Detailed Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = findViewById(R.id.detail_title);
        detail_desc = findViewById(R.id.detail_desc);
        detail_link = findViewById(R.id.detail_link);
        detail_pubDate = findViewById(R.id.detail_pubDate);

        //set values on text
        detail_title.setText(getIntent().getStringExtra("title"));
        detail_desc.setText(getIntent().getStringExtra("description"));
        detail_link.setText(getIntent().getStringExtra("link"));
        detail_pubDate.setText(getIntent().getStringExtra("pubDate"));

        String latLng = getIntent().getStringExtra("latLng");
        String[] latLngParts = latLng.split(" ");
        latTxt = latLngParts[0];
        longTxt = latLngParts[1];
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        detail_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(detail_link.getText().toString()));
                startActivity(browserIntent);
            }
        });


    }
}
