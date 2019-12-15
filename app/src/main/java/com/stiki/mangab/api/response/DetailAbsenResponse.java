package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailAbsenResponse extends BaseResponse implements Serializable {
    @SerializedName("data")
    public List<MhsData> data;

    public static class MhsData implements Serializable{
        @SerializedName("nrp")
        public String nrp;

        @SerializedName("nama")
        public String nama;

        @SerializedName("email")
        public String email;

        @SerializedName("status_absen")
        public int statusAbsen;
    }
}
