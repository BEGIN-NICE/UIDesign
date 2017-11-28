package com.example.fanxh.news.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fanxh on 2017/11/27.
 */

public class HttpRequest {
    public static Bitmap getImageBitmap(String string) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(string);
            if (url != null) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == httpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EEEEEEEE","ERROE");
        }
        return bitmap;
    }
}
