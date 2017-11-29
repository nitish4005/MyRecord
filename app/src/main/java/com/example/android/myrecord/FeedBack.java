package com.example.android.myrecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.myrecord.Network.ApiClient;
import com.example.android.myrecord.Network.ApiInterface;
import com.example.android.myrecord.NetworkModels.FeedBackResponseModel;
import com.example.android.myrecord.NetworkModels.FeedBackSumittModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBack extends AppCompatActivity {
    Button submit;
    EditText etMessage,etHeading;
    private SharedPreferences sharedpreferences;
    private String name,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        etHeading=(EditText) findViewById(R.id.headingFeedBack);
        etMessage=(EditText) findViewById(R.id.messageFeedback);
        submit = (Button) findViewById(R.id.submit);
        sharedpreferences=getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        name=sharedpreferences.getString("Name",null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id=sharedpreferences.getString("id",null);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBackSumittModel feedBackSumittModel=new FeedBackSumittModel(etHeading.getText().toString(),etMessage.getText().toString(),name,id);
                ApiInterface apiInterface = ApiClient.getApiInterface();
                Call<FeedBackResponseModel> feedbackSubmitted = apiInterface.sendFeedback(feedBackSumittModel);
                feedbackSubmitted.enqueue(new Callback<FeedBackResponseModel>() {
                    @Override
                    public void onResponse(Call<FeedBackResponseModel> call, Response<FeedBackResponseModel> response) {
                            Toast.makeText(getApplication(),"FeedBack Submitted",Toast.LENGTH_LONG).show();
                            etMessage.setText("");
                            etHeading.setText("");

                    }

                    @Override
                    public void onFailure(Call<FeedBackResponseModel> call, Throwable t) {
                         Toast.makeText(getApplicationContext(),"Connection error",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
