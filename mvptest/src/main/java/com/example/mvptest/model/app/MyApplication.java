package com.example.mvptest.model.app;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xxsj on 2017/10/30.
 */

public class MyApplication extends Application {

    private static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();


        File file = new File(Environment.getDownloadCacheDirectory().getParent() + "/MonthImage");
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(100, 100)
                .threadPoolSize(3)
                .threadPriority(100)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(build);
    }

    public static OkHttpClient okHttpClient(){
        return okHttpClient;
    }
}
