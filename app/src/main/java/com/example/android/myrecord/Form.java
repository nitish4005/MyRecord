package com.example.android.myrecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myrecord.NetworkModels.RegistrationFormResponseModelGET;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class Form extends AppCompatActivity {
    private TextView tvName, tvEnrollement, tvSemester, tvDepartmetn, tvFatherName, tvMothersName, tvMobileNo;
    private EditText etSemester, etFathersName, etMothersName, etMobileNumber;
    private Button  etSave;
    private ArrayList<RegistrationFormResponseModelGET> registrationFormlist;
    private RegistrationFormResponseModelGET registrationFormResponseModelGET = new RegistrationFormResponseModelGET("", "", "", "", "", "", "", "", "", "", "");
    private SharedPreferences sharedpreferences;
    private String id, token;
    private EditText etFathersMobile;
    private JSONObject json = new JSONObject();
    private TextView tvFatherMobile;
    private FloatingActionButton editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        tvName = (TextView) findViewById(R.id.nametv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvEnrollement = (TextView) findViewById(R.id.enrollmentTv);
        tvSemester = (TextView) findViewById(R.id.semestertv);
        tvDepartmetn = (TextView) findViewById(R.id.department);
        tvFatherName = (TextView) findViewById(R.id.fathersname);
        tvFatherMobile = (TextView) findViewById(R.id.fatherMobile);
        tvMothersName = (TextView) findViewById(R.id.mothername);
        tvMobileNo = (TextView) findViewById(R.id.mobileno);
        etFathersName = (EditText) findViewById(R.id.fathersnameEt);
        etSemester = (EditText) findViewById(R.id.semesterEditText);
        etMothersName = (EditText) findViewById(R.id.mothernameEt);
        etMobileNumber = (EditText) findViewById(R.id.mobilenoEt);
        etFathersMobile = (EditText) findViewById(R.id.fatherMobileEt);
        editButton = (FloatingActionButton) findViewById(R.id.editButton);
        etSave=(Button) findViewById(R.id.saveButton);
        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", null);
        token = sharedpreferences.getString("token_key", null);
        new getForm().execute();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    tvSemester.setVisibility(View.INVISIBLE);
                    tvFatherName.setVisibility(View.INVISIBLE);
                    tvFatherMobile.setVisibility(View.INVISIBLE);
                    tvMothersName.setVisibility(View.INVISIBLE);
                    tvMobileNo.setVisibility(View.INVISIBLE);

                    etFathersName.setText(registrationFormResponseModelGET.getFather_Name());
                    etSemester.setText(registrationFormResponseModelGET.getSemester());
                    etMothersName.setText(registrationFormResponseModelGET.getMother_Name());
                    etMobileNumber.setText(registrationFormResponseModelGET.getMobile_No());
                    etFathersMobile.setText(registrationFormResponseModelGET.getFather_Mobile_No());

                    etFathersName.setVisibility(View.VISIBLE);

                    etSemester.setVisibility(View.VISIBLE);

                    etMothersName.setVisibility(View.VISIBLE);

                    etMobileNumber.setVisibility(View.VISIBLE);

                    etFathersMobile.setVisibility(View.VISIBLE);

                    etSave.setVisibility(View.VISIBLE);

            }
        });

        etSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try{
                 json.put("Father_Name", etFathersName.getText().toString());
                json.put("Semester", etSemester.getText().toString());
                json.put("Mother_Name", etMothersName.getText().toString());
                json.put("Mobile_No", etMobileNumber.getText().toString());
                json.put("Father_Mobile_No", etFathersMobile.getText().toString());
            }catch (JSONException e) {
                    e.printStackTrace();
                }
                tvSemester.setVisibility(View.VISIBLE);
                tvFatherName.setVisibility(View.VISIBLE);
                tvFatherMobile.setVisibility(View.VISIBLE);
                tvMothersName.setVisibility(View.VISIBLE);
                tvMobileNo.setVisibility(View.VISIBLE);

                etFathersName.setVisibility(View.GONE);
                etSemester.setVisibility(View.GONE);
                etMothersName.setVisibility(View.GONE);
                etMobileNumber.setVisibility(View.GONE);
                etFathersMobile.setVisibility(View.GONE);
                new PostFunction().execute();
                etSave.setVisibility(View.GONE);

            }
        });


//        ApiInterface apiInterface = ApiClient.getApiInterface();
//        Call<List<RegistrationFormResponseModelGET>> formDetailUserCall = apiInterface.getRegistrationDetails("Bearer "+ token,id);
//        formDetailUserCall.enqueue(new Callback<List<RegistrationFormResponseModelGET>>() {
//            @Override
//            public void onResponse(Call<List<RegistrationFormResponseModelGET>> call, Response<List<RegistrationFormResponseModelGET>> response) {
//                if(response.isSuccessful()){
//                    registrationFormResponseModelGET=response.body().get(0);
//                    if(registrationFormResponseModelGET.getMsg()==null){
//
//                        tvName.append(registrationFormResponseModelGET.getName());
//                        tvEnrollement.append(registrationFormResponseModelGET.getEnrollment_No());
//                        tvSemester.append(registrationFormResponseModelGET.getSemester());
//                        tvDepartmetn.append(registrationFormResponseModelGET.getDepartment());
//                        tvFatherName.append(registrationFormResponseModelGET.getFather_Name());
//                        tvMothersName.append(registrationFormResponseModelGET.getMother_Name());
//                        tvMobileNo.append(registrationFormResponseModelGET.getMobile_No());
//                        tvFatherMobile.append(registrationFormResponseModelGET.getFather_Mobile_No());
//                    }else {
//                        Toast.makeText(getApplicationContext(),registrationFormResponseModelGET.getMsg(),Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                    Toast.makeText(getApplicationContext(),"Failed to connect",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<RegistrationFormResponseModelGET>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//
//

    }
    private class PostFunction extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            String url = "http://ec2-18-220-18-162.us-east-2.compute.amazonaws.com:3000/newform/updateform/table/Registration_Form/student/"+id;
            Log.i("NKC",url);
            URL object;
            try {
                object = new URL(url);

                HttpURLConnection urlConnection = (HttpURLConnection) object.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Authorization", "Bearer " + token);
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.connect();
                OutputStreamWriter wr= new OutputStreamWriter(urlConnection.getOutputStream());
                Log.i("NKCjson",json.toString());
                wr.write(json.toString());
                wr.close();

//display what returns the POST request

                //   StringBuilder sb = new StringBuilder();
                int HttpResult = urlConnection.getResponseCode();
                Log.i("NKC error code",urlConnection.getResponseCode()+"");
                if (HttpResult == 200 || HttpResult ==201) {
                    StringBuilder sb=new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    Log.i("NKCjson","in updating");
                    //Toast.makeText(getApplicationContext(),"Submited Sucessfully",Toast.LENGTH_LONG).show();
                } else {
                    Log.i("NKCjson","Error in updating");
                    Toast.makeText(getApplicationContext(),urlConnection.getResponseMessage(),Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Log.i("catchBlock",Log.getStackTraceString(e));
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new getForm().execute();
        }
    }

    private void update() {

        tvName.setText("Name : ");
        tvEnrollement.setText("Enrollment : ");
        tvSemester.setText("Semester : ");
        tvDepartmetn.setText("Department : ");
        tvFatherName.setText("Father Name : ");
        tvMothersName.setText("Mother Name : ");
        tvMobileNo.setText("Mobile No : ");
        tvFatherMobile.setText("Father's Mobile No : ");
        tvName.append(" " + registrationFormResponseModelGET.getName());
        tvEnrollement.append(" " + registrationFormResponseModelGET.getEnrollment_No());
        tvSemester.append(" " + registrationFormResponseModelGET.getSemester());
        tvDepartmetn.append(" " + registrationFormResponseModelGET.getDepartment());
        tvFatherName.append(" " + registrationFormResponseModelGET.getFather_Name());
        tvMothersName.append(" " + registrationFormResponseModelGET.getMother_Name());
        Log.i("NKC"," " + registrationFormResponseModelGET.getMother_Name());
        tvMobileNo.append(" " + registrationFormResponseModelGET.getMobile_No());
        tvFatherMobile.append(" " + registrationFormResponseModelGET.getFather_Mobile_No());
    }

    private class getForm extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String url = "http://ec2-18-220-18-162.us-east-2.compute.amazonaws.com:3000/newform/fetchform/table/Registration_Form/student/" + id;
            Log.i("NKCform", url);
            URL object;
            try {
                String jsonResponse = "";
                InputStream inputStream = null;
                object = new URL(url);
                Log.i("NKC", "URLCREated");
                HttpURLConnection urlConnection = (HttpURLConnection) object.openConnection();
                Log.i("NKC", "Connection Open");
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Authorization", "Bearer " + token);
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = convertStreamToString(inputStream);
                    try {
                        JSONArray jsonArr = new JSONArray(jsonResponse);

                        for (int i = 0; i < jsonArr.length(); i++) {
                            JSONObject jsonObj = jsonArr.getJSONObject(i);
                            Iterator<String> iter = jsonObj.keys();
                            while (iter.hasNext()) {
                                String k = iter.next();
                                try {
                                    Object value = jsonObj.get(k);
                                    Log.i("Info", "Key: " + k + ", value: " + value);
                                    if (k.equals("Name")) {
                                        registrationFormResponseModelGET.setName(value + "");
                                    } else if (k.equals("msg")) {
                                        Toast.makeText(getApplicationContext(), value + "", Toast.LENGTH_LONG).show();
                                    } else if (k.equals("Enrollment_No")) {
                                        registrationFormResponseModelGET.setEnrollment_No(value + "");
                                    } else if (k.equals("Department")) {
                                        registrationFormResponseModelGET.setDepartment(value + "");
                                    } else if (k.equals("Semester")) {
                                        registrationFormResponseModelGET.setSemester(value + "");
                                    } else if (k.equals("Father_Name")) {
                                        registrationFormResponseModelGET.setFather_Name(value + "");
                                    } else if (k.equals("Mother_Name")) {
                                        registrationFormResponseModelGET.setMother_Name(value + "");
                                    } else if (k.equals("Mobile_No")) {
                                        registrationFormResponseModelGET.setMobile_No(value + "");
                                    } else if (k.equals("Father_Mobile_No")) {
                                        registrationFormResponseModelGET.setFather_Mobile_No(value + "");
                                    } else if (k.equals("Father_Occupation")) {
                                        registrationFormResponseModelGET.setFather_Occupation(value + "");
                                    } else if (k.equals("Mother_Occupation")) {
                                        registrationFormResponseModelGET.setMother_Occupation(value + "");
                                    }
                                } catch (JSONException e) {
                                    // Something went wrong!
                                }
                            }

                        }

                    } catch (JSONException ex) {
                        Log.i("catchBlock", Log.getStackTraceString(ex));
                    }

                } else {
                    Log.e("NKCFormError", "" + urlConnection.getResponseCode());
                }
                urlConnection.disconnect();
                inputStream.close();

            } catch (Exception e) {
                Log.i("catchBlock", Log.getStackTraceString(e));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            update();
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
}