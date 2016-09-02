package com.utouu.test.data.retrofit;

import com.utouu.test.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marno on 2016/6/26/23:15.
 * retrofit封装
 */
public  class RetrofitHelper {

    private static String URL_BASE = BuildConfig.BASE_URL;
    private static volatile Retrofit sRetrofit;
    private static volatile RetrofitHelper sHelper;

    private RetrofitHelper() {
        sRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL_BASE)
                .build();
    }

    public static RetrofitHelper getIns() {
        if (sHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (sHelper == null) {
                    sHelper = new RetrofitHelper();
                }
            }
        }
        return sHelper;
    }

    protected static <T> T create(Class<T> apiService) {
        return sHelper.getIns().sRetrofit.create(apiService);
    }

    public GoodsService GOODS() {
        return create(GoodsService.class);
    }

}