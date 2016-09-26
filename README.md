# HMUmeng_Share
Umeng Share

[![](https://jitpack.io/v/bxcx/HMUmeng_Share.svg)](https://jitpack.io/#bxcx/HMUmeng_Share)


        new ShareUtils(context).share("耳朵纯音乐", "http://fir.im/ear", "总有一些音乐宠坏了我们的耳朵", new UMImage(this, R.mipmap.ic_ear), new IShareCallback() {
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
