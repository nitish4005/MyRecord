package com.example.android.myrecord;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myrecord.Network.ApiClient;
import com.example.android.myrecord.Network.ApiInterface;
import com.example.android.myrecord.NetworkModels.ListOfQuestions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Assignment_Started extends AppCompatActivity {
    private TextView tvQuestions,tvTimeRemaning;
    private RadioGroup rg;
    private Button btn;
    private SharedPreferences sharedpreferences;
    private ColorStateList colorStateList;
    private CountDownTimer timer;
    private int assiID, questionNumber = 0,hours,min,sec,days;
    private long durationSeconds;
    private ArrayList<ListOfQuestions> questionsList = new ArrayList<>();
    private JSONObject json = new JSONObject();
    private String id,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment__started);
        tvQuestions = (TextView) findViewById(R.id.questionTv);
        tvTimeRemaning = (TextView) findViewById(R.id.timeRemaning);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        btn = (Button) findViewById(R.id.button1);
        Bundle data = getIntent().getExtras();
        assiID = data.getInt("AssignmentID");
        durationSeconds = data.getLong("durationSeconds");
        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", null);
        token = sharedpreferences.getString("token_key",null);
        try {
            json.put("studentID", id);
            Log.i("studentID",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{

                        Color.WHITE
                        , Color.rgb(242, 81, 112),
                }
        );


        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<ArrayList<ListOfQuestions>> questionListUserCall = apiInterface.questionsList("Bearer "+ token,assiID);
        questionListUserCall.enqueue(new Callback<ArrayList<ListOfQuestions>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfQuestions>> call, Response<ArrayList<ListOfQuestions>> response) {
                if (response.isSuccessful()) {
                    Log.i("Test Response",response.body().size()+"");
                    questionsList.addAll(response.body());
                    getQuestion();
                    startCount();
                } else {
                    Log.i("TestResponse", "fail");
                    Toast.makeText(getApplicationContext(), "ErrorResponse", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListOfQuestions>> call, Throwable t) {
                Log.i("TestResult", t.getMessage());
                Toast.makeText(getApplicationContext(), "Connection Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber<questionsList.size()) {
                    int res = rg.getCheckedRadioButtonId();
                    switch (res) {
                        case 0:
                            try {
                                json.put("ques" + (questionNumber + 1), questionsList.get(questionNumber).getOp1());
                                Log.i("ques" + (questionNumber + 1),questionsList.get(questionNumber).getOp1());
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        case 1:
                            try {
                                json.put("ques" + (questionNumber + 1), questionsList.get(questionNumber).getOp2());
                                Log.i("ques" + (questionNumber + 1),questionsList.get(questionNumber).getOp2());
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        case 2:
                            try {
                                json.put("ques" + (questionNumber + 1), questionsList.get(questionNumber).getOp3());
                                Log.i("ques" + (questionNumber + 1),questionsList.get(questionNumber).getOp3());
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        case 3:
                            try {
                                json.put("ques" + (questionNumber + 1), questionsList.get(questionNumber).getOp4());
                                Log.i("ques" + (questionNumber + 1),questionsList.get(questionNumber).getOp4());
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        default:
                            try {
                                Log.i("ques" + (questionNumber + 1),"");
                                json.put("ques" + (questionNumber + 1), null);
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }

                if (questionNumber+1< questionsList.size()) {
                    rg.removeAllViews();
                    ++questionNumber;
                    getQuestion();
                } else {
                    new PostFunction().execute();
                    timer.cancel();

                }

            }
        });

    }

    protected void getQuestion() {
        tvQuestions.setText(questionNumber+1+". "+questionsList.get(questionNumber).getQues());
        rg.removeAllViews();
        AppCompatRadioButton radioButton = new AppCompatRadioButton(this);
        radioButton.setId(0);
        radioButton.setText(questionsList.get(questionNumber).getOp1());
        radioButton.setSupportButtonTintList(colorStateList);
        rg.addView(radioButton);

        radioButton = new AppCompatRadioButton(this);
        radioButton.setId(1);
        radioButton.setText(questionsList.get(questionNumber).getOp2());
        radioButton.setSupportButtonTintList(colorStateList);
        rg.addView(radioButton);

        radioButton = new AppCompatRadioButton(this);
        radioButton.setId(2);
        radioButton.setText(questionsList.get(questionNumber).getOp3());
        radioButton.setSupportButtonTintList(colorStateList);
        rg.addView(radioButton);

        radioButton = new AppCompatRadioButton(this);
        radioButton.setId(3);
        radioButton.setText(questionsList.get(questionNumber).getOp4());
        radioButton.setSupportButtonTintList(colorStateList);
        rg.addView(radioButton);

        rg.clearCheck();
    }

    protected void startCount() {
        btn.setText("SUBMIT");
        timer = new CountDownTimer(durationSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                days = (int) (millisUntilFinished / (1000*60*60*24));
                hours = (int) ((millisUntilFinished - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                min = (int) (millisUntilFinished - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                sec = (int) ((millisUntilFinished - (1000*60*60*24*days) - (1000*60*60*hours) -(1000*60*min))/(1000));
                tvTimeRemaning.setText(String.format("%02d:%02d:%02d",hours,min,sec));

            }

            @Override
            public void onFinish() {
                new PostFunction().execute();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure, you want to leave interview")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("no", null).show();

    }

    private class PostFunction extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            String url = "http://ec2-18-220-18-162.us-east-2.compute.amazonaws.com:3000/assignment/submit/student/"+id+"/assignID/"+assiID;
            Log.i("NKC",url);
            URL object;
            try {
                object = new URL(url);
                Log.i("NKC","URLCREated");
                HttpURLConnection urlConnection = (HttpURLConnection) object.openConnection();
                Log.i("NKC","Connection Open");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.connect();
                OutputStreamWriter wr= new OutputStreamWriter(urlConnection.getOutputStream());
                Log.i("NKCjson",json.toString());
                wr.write(json.toString());
                wr.close();

//display what returns the POST request

                //   StringBuilder sb = new StringBuilder();
                int HttpResult = urlConnection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    StringBuilder sb=new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    Log.i("NKC",sb.toString());
                    //Toast.makeText(getApplicationContext(),"Submited Sucessfully",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Log.i("NKC","Try again");
                    Toast.makeText(getApplicationContext(),urlConnection.getResponseMessage(),Toast.LENGTH_LONG).show();
                    btn.setText("Try Again");
                }

            } catch (Exception e) {
                Log.i("catchBlock",Log.getStackTraceString(e));
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
}
