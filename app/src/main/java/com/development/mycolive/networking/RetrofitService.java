package com.development.mycolive.networking;

import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.home.HomeResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit =null;
     private static final String URL = ApiConstant.BASE_URL;
    private ShipmentApi shipmentApi;

    public  static Retrofit getRetrofitInstance(){
      if(retrofit == null){
          HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
          loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

          OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
          httpClient.readTimeout(60, TimeUnit.SECONDS)
                  .connectTimeout(60, TimeUnit.SECONDS)
                  .writeTimeout(60, TimeUnit.SECONDS)
                  .addInterceptor(loggingInterceptor)
                  .build();

          retrofit = new Retrofit.Builder()
                  .baseUrl(URL)
                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                  .addConverterFactory(GsonConverterFactory.create())
                  .client(httpClient.build())
                  .build();
      }

      return  retrofit;

    }

    public Single<HomeResponse> getData(Map<String,String> headers){
        return shipmentApi.getHomeData(headers);
    }
}
