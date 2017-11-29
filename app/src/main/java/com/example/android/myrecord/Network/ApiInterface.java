package com.example.android.myrecord.Network;

import com.example.android.myrecord.NetworkModels.AssignmentsRecord;
import com.example.android.myrecord.NetworkModels.ChangePasswordResponseModel;
import com.example.android.myrecord.NetworkModels.FeedBackResponseModel;
import com.example.android.myrecord.NetworkModels.FeedBackSumittModel;
import com.example.android.myrecord.NetworkModels.ListOfQuestions;
import com.example.android.myrecord.NetworkModels.LoginResponseModel;
import com.example.android.myrecord.NetworkModels.PassChangeResponse;
import com.example.android.myrecord.NetworkModels.RegistrationFormResponseModelGET;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Prasad on 22-Oct-17.
 */

public interface ApiInterface {
    @POST("login")
    Call<LoginResponseModel> signInUser(@Header("Authorization")String authentication,@Query("id")String id, @Query("pass")String pass);

    @GET("assignment/semester/{semester}/department/{department}/page/1/stdid/{student_Id}")
    Call<ArrayList<AssignmentsRecord>> assignmentList(@Header("Authorization") String authentication,@Path("semester") String semester, @Path("department") String department, @Path("student_Id") String student_Id);

    @GET("assignment/student/ques/id/{assid}")
    Call<ArrayList<ListOfQuestions>> questionsList(@Header("Authorization") String authentication,@Path("assid") int assid);

    @POST("feedback/submit")
    Call<FeedBackResponseModel> sendFeedback(@Body FeedBackSumittModel feedBackSumittModel);

    @GET("newform/fetchform/table/Registration_Form/student/{stdid}")
    Call<List<RegistrationFormResponseModelGET>> getRegistrationDetails (@Header("Authorization") String authentication, @Path("stdid") String studentid);

    @POST("newform/password/{stdid}")
    Call<PassChangeResponse> passchange(@Header("Authorization") String authentication, @Path("stdid") String stdid, @Body ChangePasswordResponseModel changePasswordResponseModel);
}
