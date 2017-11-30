package com.example.fanxh.zodiacchart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mNextActivity;
    private Button mSecondUI;
    private Button mDesignView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNextActivity = (Button)findViewById(R.id.next_activity);
        mNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HuangLiActivity.class);
                startActivity(intent);
            }
        });
        mSecondUI = (Button)findViewById(R.id.second_ui);
        mSecondUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HoroscopeActivity.class);
                startActivity(intent);
            }
        });
        mDesignView = (Button)findViewById(R.id.design_view);
        mDesignView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DesignViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
