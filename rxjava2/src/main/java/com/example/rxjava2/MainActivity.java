package com.example.rxjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_SHANGYOU = "上游";
    private static final String TAG_XIAYOU = "下游";
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建容器，存放事件
        disposable = new CompositeDisposable();

      //  map();
        //发送给下游，不保证发送的顺序（无序）
        flatMap();
    }

    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG_SHANGYOU,"emitter 1");
                e.onNext(1);
                Log.i(TAG_SHANGYOU,"emitter 2");
                e.onNext(2);
                Log.i(TAG_SHANGYOU,"emitter 3");
                e.onNext(3);
                Log.i(TAG_SHANGYOU,"emitter 4");
                e.onNext(4);
                e.onComplete();
            }
        }).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<String>();
                for (int i=0;i<3;i++){
                    list.add(integer+"郝冰丽");
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {

           
        });

    }

    private void map(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG_SHANGYOU,"emitter 1");
                e.onNext(1);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return integer+"郝冰丽";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //向容器中添加数据
                disposable.add(d);
            }

            @Override
            public void onNext(String value) {
                Log.i(TAG_XIAYOU,value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity销毁时，清空容器里的事件
        disposable.clear();
    }
}
