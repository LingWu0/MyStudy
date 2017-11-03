package com.example.frescodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        //在当前Activity初始化Fresco 必须在setContentView方法之前
        setContentView(R.layout.activity_main);
        draweeView = (SimpleDraweeView) findViewById(R.id.draweeView);
        String imageUrl = "http://www.pp3.cn/uploads/201511/2015111212.jpg";
        //加载图片
        draweeView.setImageURI(imageUrl);
        DraweeController controllerBuilder = Fresco.newDraweeControllerBuilder()
                //设置路径
                .setUri(imageUrl)
                //开启重试
                .setTapToRetryEnabled(true)
                //得到控制器
                .setOldController(draweeView.getController())
                //构建
                .build();
        draweeView.setController(controllerBuilder);
    }

    public void onClick(View view) {
        Toast.makeText(this, "按压状态下", Toast.LENGTH_SHORT).show();
    }
}
