package com.example.android.myrecord;

import android.content.Context;
import android.content.Intent;
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
import com.example.android.myrecord.NetworkModels.LoginResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText userName,passWord;
    private Button login;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String access_token = "token_key";
    public static  String token=null;
    public static  String name = "";
    public static  String id = "";
    public static  String  department= "";
    SharedPreferences sharedPreferences;
    private String semester="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=(EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWord);
        login = (Button) findViewById(R.id.login);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String val = sharedPreferences.getString(access_token, null);
        if (val != null) {

            Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(intent);
            finish();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String loginId = userName.getText().toString();
                final String pass = passWord.getText().toString();
                if (loginId.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                ApiInterface apiInterface= ApiClient.getApiInterface();
                Call<LoginResponseModel> signInUserCall=apiInterface.signInUser("Basic cbpgec-a24-u26-n20-p21",loginId,pass);
                signInUserCall.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getAccess_token()!=null){
                                Log.i("test",response.message());
                                token=response.body().getAccess_token();
                                id=response.body().getId();
                                name=response.body().getName();
                                department=response.body().getDepartment();
                                semester=response.body().getSemester();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(access_token,token);
                                editor.putString("Name", name);
                                editor.putString("id",id);
                                editor.putString("semester",semester);
                                editor.putString("department",department);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(),response.body().getMessaege(), Toast.LENGTH_LONG).show();
                                return ;
                            }


                        }else {
                            Log.i("test url",response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_LONG).show();
                        Log.i("test network",t.getMessage());

                    }
                });
            }
        });

    }
}
