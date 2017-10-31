package com.example.xxsj.mystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by xxsj on 2017/10/29.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMessage1(View view){

        final MySubscriber mySubscriber = new MySubscriber();

       /* //被观察者
        Observable  observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("我们不一样");
                subscriber.onNext("浮夸");
                subscriber.onNext("惊鸿一面");
            }
           *//* @Override
            public void call(Object o) {
        //执行主要逻辑
                mySubscriber.onNext("我们不一样");
                mySubscriber.onNext("浮夸");
                mySubscriber.onNext("惊鸿一面");
            }*//*
        });*/

        Observable<String> observable = Observable.just("北京","上海");
        //订阅消息
        observable.subscribe(mySubscriber);
    }

    public void mergeMessage(View view) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2.add("a");
        list2.add("b");
        list2.add("c");

        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);
        //合并数据
        Observable observable = Observable.merge(observable2,observable1);

        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //被订阅者继承被观察者
    class MySubscriber extends Subscriber<String>{

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(MainActivity.this, "======"+s, Toast.LENGTH_SHORT).show();
        }
    }
}