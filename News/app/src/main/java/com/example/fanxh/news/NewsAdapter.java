package com.example.fanxh.news;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fanxh.news.Util.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxh on 2017/11/27.
 */

public class NewsAdapter extends ArrayAdapter {

    private View view;
    private Context mContext;
    private List<DataBean> dataList = new ArrayList<>();
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THTEE = 2;
    private ListView mListView;
    private LruCache<String, BitmapDrawable> mMemoryCache;

    private static final String MURL = "https://zkres.myzaker.com/201710/59f139eaa07aec3331000000_320.jpg";
    private int textViewResourceId;


    public NewsAdapter(Context context, int textViewResourceId, List<DataBean> objects) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
        this.dataList = objects;
        Log.e("AAAAB","pic2--->"+dataList.get(0).getPic2()+"----getPic3---->"+dataList.get(0).getPic3());
//        this.textViewResourceId = textViewResourceId;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable drawable) {
                return drawable.getBitmap().getByteCount();
            }
        };
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        Log.e("AAAA","pic2--->"+dataList.get(position).getPic2()+"----getPic3---->"+dataList.get(position).getPic3());
        if (TextUtils.isEmpty(dataList.get(position).getPic2()) && TextUtils.isEmpty(dataList.get(position).getPic3())) {
            return TYPE_ONE;
        } else if (!TextUtils.isEmpty(dataList.get(position).getPic2()) && TextUtils.isEmpty(dataList.get(position).getPic3())) {
            return TYPE_TWO;
        } else if (!TextUtils.isEmpty(dataList.get(position).getPic2()) && !TextUtils.isEmpty(dataList.get(position).getPic2())) {
            return TYPE_THTEE;
        } else {
            return 100;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (mListView == null) {
            mListView = (ListView) parent;
        }
//        private static TextView mNewsTitle=null;
//        private static TextView mAuthorName=null;
//        private static ImageView mNewsPic=null ;
//        private static ImageView mNewsPic2=null;
//        private static ImageView mNewsPic3=null;
        DataBean dataBean = (DataBean) getItem(position);
        int type = getItemViewType(position);
//        View view ;
        if (convertView == null) {
            switch (type) {
                case TYPE_ONE:
                    view = LayoutInflater.from(mContext).inflate(R.layout.news_item, null);
//                    mNewsTitle = (TextView) view.findViewById(R.id.title);
//                    mAuthorName = (TextView) view.findViewById(R.id.author_name);
//                    mNewsPic = (ImageView) view.findViewById(R.id.news_pic1);
                    break;
                case TYPE_TWO:
                    view = LayoutInflater.from(mContext).inflate(R.layout.news_item_two, null);
//                    mNewsTitle = (TextView) view.findViewById(R.id.title);
//                    mAuthorName = (TextView) view.findViewById(R.id.author_name);
//                    mNewsPic = (ImageView) view.findViewById(R.id.news_pic1);
//                    mNewsPic2 = (ImageView) view.findViewById(R.id.news_pic2);
                    break;
                case TYPE_THTEE:
                    view = LayoutInflater.from(mContext).inflate(R.layout.news_item_three, null);
//                    mNewsTitle = (TextView) view.findViewById(R.id.title);
//                    mAuthorName = (TextView) view.findViewById(R.id.author_name);
//                    mNewsPic = (ImageView) view.findViewById(R.id.news_pic1);
//                    mNewsPic2 = (ImageView) view.findViewById(R.id.news_pic2);
//                    mNewsPic3 = (ImageView) view.findViewById(R.id.news_pic3);
                    break;
                default:
                    break;
            }
        } else {
            view = convertView;
        }
//        TextView mNewsTitle = (TextView) view.findViewById(R.id.title);
//        TextView mAuthorName = (TextView) view.findViewById(R.id.author_name);
//        ImageView mNewsPic = (ImageView) view.findViewById(R.id.news_pic1);

//        Bitmap bitmap = HttpRequest.getImageBitmap(MURL);
//        Log.e("AAAAAABBBBB","mNewsPic----->"+bitmap);
//        mNewsPic.setImageBitmap(bitmap);
//        mNewsPic.setImageResource(R.drawable.ic_launcher_background);
        switch (type) {
            case TYPE_ONE:
                TextView mNewsTitle = (TextView) view.findViewById(R.id.title);
                TextView mAuthorName = (TextView) view.findViewById(R.id.author_name);
                ImageView mNewsPic = (ImageView) view.findViewById(R.id.news_pic1);
            mNewsPic.setTag(dataBean.getPic1());
            BitmapDrawable drawable = getBitmapFromMemoryCache(dataBean.getPic1());
            if (drawable != null) {
                mNewsPic.setImageDrawable(drawable);
            } else {
                BitmapWorkerTask task = new BitmapWorkerTask();
                task.execute(dataBean.getPic1());
            }
            mNewsTitle.setText(dataBean.getNewsTitle());
            mAuthorName.setText(dataBean.getAuthorName());
            break;
            case TYPE_TWO:
                TextView mNewsTitle2 = (TextView) view.findViewById(R.id.title);
                TextView mAuthorName2 = (TextView) view.findViewById(R.id.author_name);
                ImageView mNewsPic21 = (ImageView) view.findViewById(R.id.news_pic1);
                ImageView mNewsPic2 = (ImageView) view.findViewById(R.id.news_pic2);
                mNewsPic21.setTag(dataBean.getPic1());
                BitmapDrawable drawable21 = getBitmapFromMemoryCache(dataBean.getPic1());
                if (drawable21 != null) {
                    mNewsPic21.setImageDrawable(drawable21);
                } else {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    task.execute(dataBean.getPic1());
                }
                mNewsPic2.setTag(dataBean.getPic2());
                BitmapDrawable drawable2 = getBitmapFromMemoryCache(dataBean.getPic2());
                if (drawable2 != null) {
                    mNewsPic2.setImageDrawable(drawable2);
                } else {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    task.execute(dataBean.getPic2());
                }
                mNewsTitle2.setText(dataBean.getNewsTitle());
                mAuthorName2.setText(dataBean.getAuthorName());
                break;
            case TYPE_THTEE:
                TextView mNewsTitle3 = (TextView) view.findViewById(R.id.title);
                TextView mAuthorName3 = (TextView) view.findViewById(R.id.author_name);
                ImageView mNewsPic31 = (ImageView) view.findViewById(R.id.news_pic1);
                ImageView mNewsPic32 = (ImageView) view.findViewById(R.id.news_pic2);
                ImageView mNewsPic3 = (ImageView) view.findViewById(R.id.news_pic3);
                mNewsPic31.setTag(dataBean.getPic1());
                BitmapDrawable drawable31 = getBitmapFromMemoryCache(dataBean.getPic1());
                if (drawable31 != null) {
                    mNewsPic31.setImageDrawable(drawable31);
                } else {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    task.execute(dataBean.getPic1());
                }
                mNewsPic32.setTag(dataBean.getPic2());
                BitmapDrawable drawable32 = getBitmapFromMemoryCache(dataBean.getPic2());
                if (drawable32 != null) {
                    mNewsPic32.setImageDrawable(drawable32);
                } else {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    task.execute(dataBean.getPic2());
                }
                mNewsPic3.setTag(dataBean.getPic3());
                BitmapDrawable drawable3 = getBitmapFromMemoryCache(dataBean.getPic3());
                if (drawable3 != null) {
                    mNewsPic3.setImageDrawable(drawable3);
                } else {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    task.execute(dataBean.getPic3());
                }
                mNewsTitle3.setText(dataBean.getNewsTitle());
                mAuthorName3.setText(dataBean.getAuthorName());
                break;
                default:
                    break;
        }
        return view;
    }


    /**
     * 将一张图片存储到LruCache中
     */
    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中去一张图片
     */
    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 异步下载图片
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        String imageUrl;

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = HttpRequest.getImageBitmap(imageUrl);
            BitmapDrawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }
    }
}
