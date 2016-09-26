package com.hm.library.umeng.share;

import java.util.Map;

public interface ILoginCallback {
    void onSuccess(Map<String, String> data);
    void onFaild(String msg);
    void onCancel();
}
