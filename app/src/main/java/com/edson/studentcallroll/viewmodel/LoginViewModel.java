package com.edson.studentcallroll.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edson.studentcallroll.apidata.network.RetroClient;
import com.edson.studentcallroll.apidata.request.LoginRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> message = new MutableLiveData<>();
    private String token;
    private String lastName;
    private String firstName;
    private String identifierNumber;

    public LoginViewModel() {
    }

    public MutableLiveData<String> userLogin() {
        Call<ResponseBody> call = RetroClient.getInstance().getApi().userLogin(new LoginRequest(email.getValue(), password.getValue()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JsonParser parser = new JsonParser();
                    if (response.isSuccessful()){
                        JsonObject jso = (JsonObject) parser.parseString(response.body().string());
                        token = jso.get("token").getAsString();
                        lastName = jso.get("lastName").getAsString();
                        firstName = jso.get("firstname").getAsString();
                        identifierNumber = jso.get("identifierNumber").getAsString();
                        message.postValue("Se connecter correct !");//response.message() ?
                    } else {
                        JsonObject jso = (JsonObject) parser.parseString(response.errorBody().string());
                        message.postValue(jso.get("message").getAsString());//errorBody
                    }
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
                Log.i("LOGIIIIIN", "\nonResponseCode: \n" + response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                message.postValue(t.getMessage());
            }
        });
        return message;
    }

    public String getToken() {
        return this.token;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getIdentifierNumber() {
        return identifierNumber;
    }
}
