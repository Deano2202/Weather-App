package com.example.weatherapp.UserInterface;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.UserInterface.Locations.London;
import com.example.weatherapp.UserInterface.Locations.Glasgow;
import com.example.weatherapp.UserInterface.Locations.NewYork;
import com.example.weatherapp.UserInterface.Locations.Oman;
import com.example.weatherapp.UserInterface.Locations.Mauritius;
import com.example.weatherapp.UserInterface.Locations.Bangladesh;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drop_down_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handling the item selection
        switch (item.getItemId()) {
            case R.id.LondonFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new London()).commit();
                getSupportActionBar().setTitle("London");
                break;

            case R.id.GlasgowFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Glasgow()).commit();
                getSupportActionBar().setTitle("Glasgow");
                break;

            case R.id.NewYorkFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new NewYork()).commit();
                getSupportActionBar().setTitle("NewYork");
                break;

            case R.id.OmanFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Oman()).commit();
                getSupportActionBar().setTitle("Oman");
                break;

            case R.id.MauritiusFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Mauritius()).commit();
                getSupportActionBar().setTitle("Mauritius");
                break;

            case R.id.BangladeshFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Bangladesh()).commit();
                getSupportActionBar().setTitle("Bangladesh");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("London");
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new London()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Glasgow()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new NewYork()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Oman()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Mauritius()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Bangladesh()).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.LondonFragment);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    switch (item.getItemId()) {
                        case R.id.LondonFragment:
                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new London()).commit();
                            getSupportActionBar().setTitle("London");
                            return true;
                    }
                }
                return false;
            }
        });
    }
}