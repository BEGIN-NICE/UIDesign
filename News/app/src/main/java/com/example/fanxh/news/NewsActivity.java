package com.example.fanxh.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fanxh.news.Util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private List<DataBean>dataBeanList1 = new ArrayList<>();
    private ListView mNewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        String string = JsonUtil.getJson(this,"news.txt");
        dataBeanList1 = JsonUtil.parseJsonWithJsonObject(string);
        final NewsAdapter newsAdapter = new NewsAdapter(this,0,dataBeanList1);
        mNewsList = (ListView)findViewById(R.id.news_list);
        mNewsList.setAdapter(newsAdapter);
        mNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataBean dataBean = dataBeanList1.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(dataBean.getUrl()));
                startActivity(intent);
            }
        });
    }
}
