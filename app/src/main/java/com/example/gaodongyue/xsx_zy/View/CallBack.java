package com.example.gaodongyue.xsx_zy.View;

/**
 * Created by m on 2018/1/2.
 */
public interface CallBack<T> {
    void success(T t);
    void failure(Exception e);
}
