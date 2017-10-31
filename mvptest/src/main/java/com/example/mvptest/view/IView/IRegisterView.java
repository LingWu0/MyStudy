package com.example.mvptest.view.IView;

import java.io.IOException;

/**
 * Created by xxsj on 2017/10/30.
 */

public interface IRegisterView {

    void onRegisterSucced(String dataBean);
    void onRegisterFail(IOException exception);
}
