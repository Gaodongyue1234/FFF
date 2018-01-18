package com.example.gaodongyue.xsx_zy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gaodongyue.xsx_zy.Presenter.Presenter;
import com.example.gaodongyue.xsx_zy.View.ViewInf;
import com.example.gaodongyue.xsx_zy.adapter.MyAdapter;
import com.example.gaodongyue.xsx_zy.bean.Data;
import com.google.gson.Gson;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewInf<String> {

    private RecyclerView recy;
    private Presenter p;
    private List<Data.DataBean.ObjectsBean> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        p = new Presenter(this);
        initView();

    }

    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);
        p.send("http://huixinguiyu.cn/Assets/js/competitive.js");
        LinearLayoutManager manager = new LinearLayoutManager(HomeActivity.this);
        recy.setLayoutManager(manager);
    }

    @Override
    public void success(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Data data = gson.fromJson(s, Data.class);
                Data.DataBean dataBean = data.getData();
               list = dataBean.getObjects();


                myAdapter = new MyAdapter(list, HomeActivity.this);
                recy.setAdapter(myAdapter);
                myAdapter.setOnItemListener(new MyAdapter.OnItemClick() {
                    @Override
                    public void setOnItem(View v, int position) {
                        Intent intent = new Intent(HomeActivity.this, XiangQActivity.class);
                        intent.putExtra("aaa", HomeActivity.this.list.get(position).getGmall_product().getPic_url());
                        intent.putExtra("bbb", HomeActivity.this.list.get(position).getGmall_product().getTitle());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void failure(Exception e) {

    }
}
