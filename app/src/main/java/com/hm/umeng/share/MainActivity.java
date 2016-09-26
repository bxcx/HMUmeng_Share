package com.hm.umeng.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hm.library.umeng.share.IShareCallback;
import com.hm.library.umeng.share.ShareUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class MainActivity extends Activity {
    private ShareUtils su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        su = new ShareUtils(this);

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();

                //                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
                //                        .withText("hello")
                //                        .setCallback(umShareListener)
                //                        .share();


                //                su.login(SHARE_MEDIA.QQ, new ILoginCallback() {
                //                    @Override
                //                    public void onSuccess(Map<String, String> data) {
                //                        Toast.makeText(MainActivity.this, "用户信息：" + data.toString(), Toast.LENGTH_SHORT).show();
                //                    }
                //
                //                    @Override
                //                    public void onFaild(String msg) {
                //                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                //                    }
                //
                //                    @Override
                //                    public void onCancel() {
                //                        Toast.makeText(MainActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
                //                    }
                //                });
            }
        });
    }

    public void share() {

        su.share("耳朵纯音乐", "http://fir.im/ear", "总有一些音乐宠坏了我们的耳朵", new UMImage(this, R.mipmap.ic_ear), new IShareCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFaild() {
                Toast.makeText(MainActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
            }
        });

        //        ShareModel model = new ShareModel();
        //        model.setTitle("测试分享标题");
        //        model.setTargetUrl("http://ear.life");
        //        model.setContent("测试分享内容");
        //        model.setImageMedia(new UMImage(this, R.mipmap.ic_launcher));
        //        su.share(model, new IShareCallback() {
        //            @Override
        //            public void onSuccess() {
        //                Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        //            }
        //
        //            @Override
        //            public void onFaild() {
        //                Toast.makeText(MainActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
        //            }
        //
        //            @Override
        //            public void onCancel() {
        //                Toast.makeText(MainActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
        //            }
        //        });


    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.e("com.hm.library.umeng", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(MainActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.e("com.hm.library.umeng", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            Log.e("com.hm.library.umeng", platform + " 分享取消了");
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        su.onActivityResult(requestCode, resultCode, data);
    }
}