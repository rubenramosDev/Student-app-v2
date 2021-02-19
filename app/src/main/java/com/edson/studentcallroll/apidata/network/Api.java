package com.edson.studentcallroll.apidata.network;

import com.edson.studentcallroll.apidata.request.LoginRequest;
import com.edson.studentcallroll.apidata.request.SingupRequest;
import com.edson.studentcallroll.apidata.request.TakeAssistanceRequest;
import com.edson.studentcallroll.apidata.request.UpdateInfoRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<ResponseBody> userLogin(@Body LoginRequest body);

    @Headers("Content-Type: application/json")
    @POST("auth/api/assistance-sheet/assistance")
    Call<ResponseBody> takeAssistance(@Header("Authorization") String token,
                                      @Body TakeAssistanceRequest body);

    @GET("auth/api/student/view-my-assistances/{identifierNumber}")
    Call<ResponseBody> getMyAssistances(@Header("Authorization") String token,
                                        @Path("identifierNumber") int identifierNumber);

    @GET("auth/api/student/see/{identifierNumber}")
    Call<ResponseBody> getMyInformation(@Header("Authorization") String token,
                                        @Path("identifierNumber") int identifierNumber);

    @Headers("Content-Type: application/json")
    @POST("auth/api/student/register")
    Call<ResponseBody> studentSingup(@Body SingupRequest body);

    @Headers("Content-Type: application/json")
    @PUT("api/teacher/update")
    Call<ResponseBody> updateMyInformation(@Header("Authorization") String token,
                                           @Body UpdateInfoRequest body);
}
