package com.edson.studentcallroll.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edson.studentcallroll.apidata.network.RetroClient;
import com.edson.studentcallroll.apidata.request.TakeAssistanceRequest;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadQrCodeViewModel extends ViewModel {

    private int httpStatusCode;

    MutableLiveData<String> reponse;

    public MutableLiveData<String> takeAssistance(String token, long assistanceSheetId, String identifierNumber){
        Call<ResponseBody> call = RetroClient.getInstance().getApi().takeAssistance(token, new TakeAssistanceRequest(assistanceSheetId,identifierNumber));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    reponse.postValue("Bien enregistré !");
                }else {
                    try {
                        reponse.postValue("Une erreur est survenue !\n" + response.errorBody().string());
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                httpStatusCode = response.code();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                reponse.postValue("Failure: " + t.getMessage());
            }
        });
        return reponse;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
