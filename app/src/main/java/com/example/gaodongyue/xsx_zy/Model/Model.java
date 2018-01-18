package com.example.gaodongyue.xsx_zy.Model;

import com.example.gaodongyue.xsx_zy.View.CallBack;
import com.example.gaodongyue.xsx_zy.View.OkHttpTools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



/**
 * Created by m on 2018/1/2.
 */
public class Model implements ModelInf {
    @Override
    public void sendRequest(String url, CallBack callBack) {
        Class type = getSuperClassGenricType(callBack.getClass(), 0);
        OkHttpTools.getInstance().sendRequest(type, url, callBack);
    }

    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }
}
