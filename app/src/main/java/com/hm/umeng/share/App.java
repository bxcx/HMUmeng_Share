package com.hm.umeng.share;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * App
 * <p>
 * himi on 2016-09-26 07:32
 * version V1.0
 */
public class App extends Application {
    {

        PlatformConfig.setWeixin("wxfd90f99c0cc74b6b", "d2221c57b74142b819716b2e07b03995");
        PlatformConfig.setSinaWeibo("4095358359", "deaa7d29ca46905620600ab8e62a345e");
        PlatformConfig.setQQZone("1105718030", "8WYKGmpNUOm2NZah");

//        ShareUtils.setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ);
    }
}
