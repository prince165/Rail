package com.example.bhartiyarail.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PnrStatusResponse {
    @SerializedName("pnr_status")
    @Expose
    private List<PnrStatus> pnrStatus = null;

    public List<PnrStatus> getPnrStatus() {
        return pnrStatus;
    }

    public void setPnrStatus(List<PnrStatus> pnrStatus) {
        this.pnrStatus = pnrStatus;
    }
}
