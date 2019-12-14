package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailAbsenResponse extends BaseResponse {
    @SerializedName("data")
    public List<MhsData> data;

    static class MhsData{
        @SerializedName("nrp")
        public String nrp;

        @SerializedName("nama")
        public String nama;

        @SerializedName("email")
        public String email;

        @SerializedName("status_absen")
        public String statusAbsen;
    }
}
