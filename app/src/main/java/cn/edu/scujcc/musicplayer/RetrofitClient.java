package cn.edu.scujcc.musicplayer;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * 使用单例模式创建Retrofit对象。
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            Moshi moshi = new Moshi.Builder()
                    .add(new MyDateAdapter())
                    .build();
            //准备拦截器
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor())
                    .build();

            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://47.115.154.152:8080")  //改为自己的阿里云服务器IP
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .callFactory(client)  //注入自定义的okhttp
                    .build();
        }
        return INSTANCE;
    }
}
