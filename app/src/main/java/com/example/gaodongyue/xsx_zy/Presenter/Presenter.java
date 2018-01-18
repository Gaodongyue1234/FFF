package com.example.gaodongyue.xsx_zy.Presenter;

import com.example.gaodongyue.xsx_zy.View.CallBack;
import com.example.gaodongyue.xsx_zy.Model.Model;
import com.example.gaodongyue.xsx_zy.Model.ModelInf;
import com.example.gaodongyue.xsx_zy.View.ViewInf;

/**
 * Created by m on 2018/1/2.
 */
public class Presenter {
    private ViewInf iview;
    private ModelInf moDel;
    public Presenter(ViewInf iview){
        this.iview=iview;
        moDel = new Model();
    }
    public void send(String url){
        moDel.sendRequest(url, new CallBack() {

            @Override
            public void success(Object s) {
                iview.success(s);
            }

            @Override
            public void failure(Exception e) {
                iview.failure(e);
            }
        });

    }
}
