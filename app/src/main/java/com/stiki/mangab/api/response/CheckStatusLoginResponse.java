package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

public class CheckStatusLoginResponse extends BaseResponse {
    @SerializedName("type")
    public String type;

    @SerializedName("no_induk")
    public String noInduk;

    @SerializedName("nama")
    public String nama;

    @SerializedName("email")
    public String email;
}