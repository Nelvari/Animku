package com.example.animku.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.animku.Fragment.GenreFragment;
import com.example.animku.Fragment.HomeFragment;
import com.example.animku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainFragment extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navGenre:
                    selectedFragment = new GenreFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
                        realm = Realm.getInstance(configuration);
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();
    }
}