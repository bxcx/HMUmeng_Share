package com.hm.library.umeng.share;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Arrays;
import java.util.Map;

public class ShareUtils {
    private UMShareAPI mShareAPI = null;
    private Activity c;
    private ILoginCallback loginCallback;
    private IShareCallback shareCallback;


    public static SHARE_MEDIA[] displaylist = null;

    public static SHARE_MEDIA[] getDisplaylist() {
        if (displaylist == null || displaylist.length == 0) {
            setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE);
        }
        return displaylist;
    }

    public ShareUtils(Activity context) {
        this.c = context;
        if (mShareAPI == null) {
            mShareAPI = UMShareAPI.get(context);
        }
    }

    public static void setDisplayList(SHARE_MEDIA... list) {
        displaylist = (SHARE_MEDIA[]) Arrays.asList(list).toArray();
    }

    public void login(SHARE_MEDIA platform, ILoginCallback callback) {
        this.loginCallback = callback;
        mShareAPI.doOauthVerify(c, platform, umAuthListener);
    }

    public void share(String title, String url, String content, UMImage imageMedia, IShareCallback shareCallback) {
        this.shareCallback = shareCallback;
        new ShareAction(c).setDisplayList(getDisplaylist())
                .withTitle(title)
                .withTargetUrl(url)
                .withText(content)
                .withMedia(imageMedia)
                .setCallback(umShareListener)
                .open();
    }

    public void share(ShareModel model, IShareCallback shareCallback) {
        this.shareCallback = shareCallback;
        new ShareAction(c).setDisplayList(getDisplaylist())
                .withTitle(model.getTitle())
                .withTargetUrl(model.getTargetUrl())
                .withText(model.getContent())
                .withMedia(model.getImageMedia())
                .setCallback(umShareListener)
                .open();
    }

    /**********
     * 内部方法
     ***********/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.d("HMUmeng", "登陆授权获取成功" + data);
            if (data != null) {
                mShareAPI.getPlatformInfo(c, platform, getInfoListener);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.d("HMUmeng", "授权失败");
            loginCallback.onFaild("授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.d("HMUmeng", "onCancel");
            loginCallback.onCancel();
        }
    };
    private UMAuthListener getInfoListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.d("HMUmeng", "获取用户信息成功" + data);
            if (data != null) {
                loginCallback.onSuccess(data);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.d("HMUmeng", "获取用户信息失败");
            loginCallback.onFaild("获取用户信息失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.d("HMUmeng", "onCancel");
            loginCallback.onCancel();
        }
    };
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            shareCallback.onSuccess();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            shareCallback.onFaild();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            shareCallback.onCancel();
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
