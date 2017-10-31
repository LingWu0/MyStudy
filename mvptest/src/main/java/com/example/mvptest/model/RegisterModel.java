package com.example.mvptest.model;

import android.support.annotation.NonNull;

import com.example.mvptest.model.app.MyApplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xxsj on 2017/10/30.
 */

public class RegisterModel {
    //构造方法
    public RegisterModel(){

    }

    public void Register(@NonNull final DataCallBack<String> dataCallBack){
        OkHttpClient okHttpClient = MyApplication.okHttpClient();
        Request request = new Request.Builder()
                .url("http://news-at.zhihu.com/api/4/news/before/20131119")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataCallBack.GetDataFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        dataCallBack.GetDataSucced(json);
                    }
            }
        });


    }

    public interface DataCallBack<T>{
        void GetDataSucced(T t);
        void GetDataFailed(IOException s);
    }
}
