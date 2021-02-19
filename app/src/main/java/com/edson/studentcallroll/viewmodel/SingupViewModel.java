package com.edson.studentcallroll.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edson.studentcallroll.apidata.network.RetroClient;
import com.edson.studentcallroll.apidata.request.SingupRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingupViewModel extends ViewModel {

    private MutableLiveData<String> message = new MutableLiveData<>();

    public SingupViewModel() {
    }

    public MutableLiveData<String> singupStudent(String email, String identifierNumber, String lastName, String firstName, String password) {
        Call<ResponseBody> call = RetroClient.getInstance().getApi().studentSingup(
                new SingupRequest(email, identifierNumber, lastName,
                        firstName, password));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JsonParser parser = new JsonParser();
                try {
                    if (response.isSuccessful()) {
                        JsonObject jso = (JsonObject) parser.parseString(response.body().string());
                        message.postValue(jso.get("message").getAsString());
                    } else {
                        message.postValue(null);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return message;
    }
}
