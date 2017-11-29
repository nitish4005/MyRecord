package com.example.android.myrecord.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prasad on 22-Oct-17.
 */

public class ApiClient {
    static ApiInterface apiInterface;
    public static ApiInterface getApiInterface(){
        if(apiInterface==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-18-220-18-162.us-east-2.compute.amazonaws.com:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface;

    }
}
