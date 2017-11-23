package com.example.fanxh.zodiacchart;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HoroscopeActivity extends AppCompatActivity {
    private TextView mGodsOrientation;
    private TextView mLunarDate;
    private TextView mDing;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mGodsOrientation = (TextView) findViewById(R.id.gods_orientation);
        mLunarDate = (TextView) findViewById(R.id.lunar_date);
        mDing = (TextView) findViewById(R.id.ding);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "CalendarSongTi.ttf");
        mGodsOrientation.setTypeface(typeface);
        mLunarDate.setTypeface(typeface);
        mDing.setBackground(getResources().getDrawable(R.drawable.almanac_bg_luckytime));
        mDing.setTextColor(getResources().getColor(R.color.colorWhite));

    }
}
