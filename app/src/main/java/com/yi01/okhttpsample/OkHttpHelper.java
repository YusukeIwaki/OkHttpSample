package com.yi01.okhttpsample;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by yi01 on 2015/08/21.
 */
public class OkHttpHelper {
    private static OkHttpClient sClient = null;

    private static OkHttpClient newClient(Context appContext) {
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(0, TimeUnit.SECONDS);
        client.setReadTimeout(4, TimeUnit.SECONDS);
        client.setWriteTimeout(12, TimeUnit.SECONDS);

        return client;
    }

    public static final OkHttpClient getClient(Context appContext) {
        if (sClient==null) sClient = newClient(appContext);
        return sClient;
    }
}
