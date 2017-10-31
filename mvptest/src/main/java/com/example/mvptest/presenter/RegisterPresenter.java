package com.example.mvptest.presenter;

import android.support.annotation.NonNull;

import com.example.mvptest.model.RegisterModel;
import com.example.mvptest.view.IView.IRegisterView;

import java.io.IOException;

/**
 * Created by xxsj on 2017/10/30.
 */

public class RegisterPresenter {

    private IRegisterView iRegisterView;
    private RegisterModel registerModel;

    public RegisterPresenter(@NonNull IRegisterView iRegisterView){
        this.iRegisterView = iRegisterView;
        registerModel = new RegisterModel();

    }

    public void Register(){
        registerModel.Register(new RegisterModel.DataCallBack<String>() {
            @Override
            public void GetDataSucced(String s) {
                iRegisterView.onRegisterSucced(s);
            }

            @Override
            public void GetDataFailed(IOException s) {
                iRegisterView.onRegisterFail(s);
            }
        });
    }
}
