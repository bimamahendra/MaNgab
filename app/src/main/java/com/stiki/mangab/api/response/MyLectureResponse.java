package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class MyLectureResponse extends BaseResponse {
    @SerializedName("data")
    public List<MyLectureData> data;

    public static class MyLectureData{
        @SerializedName("kode")
        public String kode;

        @SerializedName("nama")
        public String nama;

        @NonNull
        @Override
        public String toString() {
            return nama;
        }
    }
}
