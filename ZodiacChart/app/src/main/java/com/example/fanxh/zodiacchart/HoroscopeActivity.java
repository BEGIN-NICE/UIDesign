package com.example.fanxh.zodiacchart;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fanxh.zodiacchart.Util.SetStatusBar;

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
        new SetStatusBar(this, R.drawable.bg_notification);
        mGodsOrientation = (TextView) findViewById(R.id.gods_orientation);
        mLunarDate = (TextView) findViewById(R.id.lunar_date);
        mDing = (TextView) findViewById(R.id.ding);
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "CalendarSongTi.ttf");
            mGodsOrientation.setTypeface(typeface);
            mLunarDate.setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDing.setBackgroundResource(R.drawable.almanac_bg_luckytime);
        mDing.setTextColor(getResources().getColor(R.color.colorWhite));

    }
}
