package com.example.gaodongyue.xsx_zy.View;

import java.io.IOException;
import java.lang.reflect.Type;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by m on 2018/1/2.
 */
public class OkHttpTools {
    private OkHttpClient okHttpClient;
    private static OkHttpTools tools;

    private OkHttpTools() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static OkHttpTools getInstance() {
        if (tools == null) {
            synchronized (OkHttpTools.class) {
                if (tools == null)
                    tools = new OkHttpTools();
            }
        }
        return tools;
    }

    public void sendRequest(final Type type, String url, final CallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.success(response.body().string());
            }
        });
    }
}
