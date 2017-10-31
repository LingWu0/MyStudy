package com.example.mvptest.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mvptest.R;
import com.example.mvptest.model.bean.DataBean;
import com.example.mvptest.presenter.RegisterPresenter;
import com.example.mvptest.view.IView.IRegisterView;
import com.example.mvptest.view.adapter.RecyclerViewAdapter;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IRegisterView {

    private Banner imgbanner;
    private RecyclerView recyclerView;
    private RegisterPresenter registerPresenter;
    private List<DataBean.StoriesBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        registerPresenter = new RegisterPresenter(this);
        registerPresenter.Register();

        ArrayList<String> imgs = new ArrayList<>();
        imgs.add("https://pic3.zhimg.com/v2-9c1568aa03ca151eea4a587ee51802ea.jpg");
        imgs.add("https://pic1.zhimg.com/v2-05dc0ad139f217f283875815bc5538a0.jpg");
        imgs.add("https://pic2.zhimg.com/v2-a4ebecc5f3ac7845805b2d17688db35d.jpg");
        imgs.add("https://pic1.zhimg.com/v2-31c7577a439db633b92b2be42caf1e64.jpg");
        imgs.add("https://pic1.zhimg.com/v2-ccd5abcab2fe67c945245e1e8781d550.jpg");

        imgbanner.setImageLoader(new GlideImageLoader());
        imgbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        imgbanner.setImages(imgs);
        imgbanner.setDelayTime(1500);
        imgbanner.isAutoPlay(true);
        imgbanner.start();

    }

    @Override
    public void onRegisterSucced(String dataBean) {

        Gson gson = new Gson();
        DataBean bean = gson.fromJson(dataBean, DataBean.class);
        list.addAll(bean.getStories());
        runOnUiThread(new Runnable() {

            private RecyclerViewAdapter adapter;
            private LinearLayoutManager linearLayoutManager;

            @Override
            public void run() {
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new RecyclerViewAdapter(MainActivity.this,list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View v, List<String> images, String title) {
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onRegisterFail(IOException exception) {

    }

    //加载轮播的图片
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)//让图片进行内存缓存
                    .cacheOnDisk(true)//让图片进行sdcard缓存
                    .showImageOnLoading(R.mipmap.ic_launcher)//图片正在加载的时候显示的图片
                    .build();
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path, imageView, options);
        }

    }
        private void initview() {
            imgbanner = (Banner) findViewById(R.id.imgbanner);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        }

}
