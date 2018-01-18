package com.example.gaodongyue.xsx_zy;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class XiangQActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button btn_send;
    private ImageView image;
    private String aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_q);
        initView();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        String aaa = intent.getStringExtra("aaa");
        String bbb = intent.getStringExtra("bbb");
        Picasso.with(XiangQActivity.this).load(aaa).into(image);
        title.setText(bbb);
        this.aaa=intent.getStringExtra("aaa");
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    private void initView() {
        btn_send = (Button) findViewById(R.id.btn_send);
        image = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);

        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UMImage image = new UMImage(XiangQActivity.this,aaa);
        switch (v.getId()) {
            case R.id.btn_send:
                new ShareAction(XiangQActivity.this)
                        .withText("molu")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
                break;
        }
    }
}

