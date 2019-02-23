package com.example.android.simpol;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements Me.OnFragmentInteractionListener {

    private static Context context;
    Fragment selected = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_politicians:
                    selected = new Politicians();
                    Log.d("LLL", "LLL");
                    break;
                case R.id.navigation_issues:
                    //mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.navigation_me:
                    selected = new Me();
                    break;
                case R.id.navigation_voting:
                    //mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.navigation_setting:
                    //mTextMessage.setText(R.string.title_notifications);
                    break;
                default:
                    return false;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();
        // navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static Context getAppContext(){
        return MainActivity.context;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
