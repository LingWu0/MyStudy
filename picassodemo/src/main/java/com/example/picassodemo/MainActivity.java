package com.example.picassodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.GrayscaleTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load("http://img02.sogoucdn.com/app/a/100520093/2a5ac85aaaa2d951-48ebc1dba4f3112d-7022ab585b7468744cee99e7cfc47231.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .transform(new GrayscaleTransformation())
                .fit()
                .centerCrop()
               // .rotate(60)
                .into(imageView);

    }
}
