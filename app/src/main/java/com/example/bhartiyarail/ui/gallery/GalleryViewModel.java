package com.example.bhartiyarail.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bhartiyarail.network.ApiInterface;
import com.example.bhartiyarail.network.ErrorResponse;
import com.example.bhartiyarail.network.PnrStatusResponse;
import com.example.bhartiyarail.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

     MutableLiveData<PnrStatusResponse> pnrResponse = new MutableLiveData<PnrStatusResponse>();
     MutableLiveData<ErrorResponse> errorResponseData = new MutableLiveData<ErrorResponse>();

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getPnrResponse(String trainNumer){
        ApiInterface retrofitClient = RetrofitClient.getRetrofit();
        Call<PnrStatusResponse> pnrCall = retrofitClient.getPnrStatus(trainNumer);
        pnrCall.enqueue(new Callback<PnrStatusResponse>() {
            @Override
            public void onResponse(Call<PnrStatusResponse> call, Response<PnrStatusResponse> response) {
               if (response.isSuccessful()){
                   pnrResponse.setValue(response.body());
               } else{
                   ErrorResponse errorResponse = new ErrorResponse();
                   errorResponse.setCode(response.code());
                   errorResponse.setMessage(response.message());

                   errorResponseData.setValue(errorResponse);
               }
            }

            @Override
            public void onFailure(Call<PnrStatusResponse> call, Throwable t) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode(-1);
                errorResponse.setMessage(t.getMessage());
                errorResponseData.setValue(errorResponse);
            }
        });
    }
}