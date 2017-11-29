package com.example.android.myrecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.myrecord.Network.ApiClient;
import com.example.android.myrecord.Network.ApiInterface;
import com.example.android.myrecord.NetworkModels.ChangePasswordResponseModel;
import com.example.android.myrecord.NetworkModels.PassChangeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    private EditText newPass,currentPass;
    private SharedPreferences sharedpreferences;
    private String strCurrPass,strNewPass;
    private Button change;
    private ChangePasswordResponseModel changePasswordResponseModel = new ChangePasswordResponseModel();
    private String id,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newPass=(EditText) findViewById(R.id.newpassword);
        currentPass=(EditText) findViewById(R.id.CurrentpassWord);
        change=(Button) findViewById(R.id.ChangePassword);
        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", null);
        token = sharedpreferences.getString("token_key",null);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCurrPass=currentPass.getText().toString();
                strNewPass=newPass.getText().toString();
                if(strCurrPass.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }else if(strNewPass.length()<7){
                    Toast.makeText(getApplicationContext(),"password should be minimum 8 characters",Toast.LENGTH_LONG).show();
                    return;
                }

                changePasswordResponseModel.setOld(strCurrPass);
                changePasswordResponseModel.setConfirmOld(strCurrPass);
                changePasswordResponseModel.setNewpass(strNewPass);

                ApiInterface apiInterface= ApiClient.getApiInterface();
                Call<PassChangeResponse> passChangeResponseCall = apiInterface.passchange("Bearer "+ token,id,changePasswordResponseModel);
                passChangeResponseCall.enqueue(new Callback<PassChangeResponse>() {
                    @Override
                    public void onResponse(Call<PassChangeResponse> call, Response<PassChangeResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PassChangeResponse> call, Throwable t) {
                        //Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("OnFAIL",t.getMessage());
                    }
                });
            }
        });
        

    }

}
