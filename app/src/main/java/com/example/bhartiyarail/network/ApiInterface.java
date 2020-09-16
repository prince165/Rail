package com.example.bhartiyarail.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("pnrStatus.json")
    Call<PnrStatusResponse> getPnrStatus(@Query("train_no") String trainnumer);

    @GET("sourceDest.json")
    Call<SourceDestResponse> getSourceDest();

}
