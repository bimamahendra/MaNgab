package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyClassResponse extends BaseResponse {
    @SerializedName("data")
    public List<MyClassData> data;

    static class MyClassData{
        @SerializedName("id_matkul")
        public String idMatkul;

        @SerializedName("kelas")
        public String kelas;
    }
}
