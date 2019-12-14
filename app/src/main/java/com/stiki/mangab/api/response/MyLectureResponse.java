package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyLectureResponse extends BaseResponse {
    @SerializedName("data")
    public List<MyLectureData> data;

    static class MyLectureData{
        @SerializedName("kode")
        public String kode;

        @SerializedName("nama")
        public String nama;
    }
}
