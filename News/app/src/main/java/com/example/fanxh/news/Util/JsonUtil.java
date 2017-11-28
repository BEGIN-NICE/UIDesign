package com.example.fanxh.news.Util;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.fanxh.news.DataBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxh on 2017/11/27.
 */

public class JsonUtil {
    public static List<DataBean>dataBeanList;
    public static String getJson(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

    public static List<DataBean> parseJsonWithJsonObject(String jsonData) {
        try {
            dataBeanList = new ArrayList<>();
            JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DataBean dataBean = new DataBean();
                dataBean.setNewsDate(jsonObject.getString("newsDate"));
                dataBean.setNewsTitle(jsonObject.getString("newsTitle"));
                dataBean.setCategory(jsonObject.getString("category"));
                dataBean.setAuthorName(jsonObject.getString("authorName"));
                dataBean.setUrl(jsonObject.getString("url"));
                dataBean.setPic1(jsonObject.getString("pic1"));
                dataBean.setPic2(jsonObject.getString("pic2"));
                dataBean.setPic3(jsonObject.getString("pic3"));
                dataBeanList.add(dataBean);
            }
            return dataBeanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
