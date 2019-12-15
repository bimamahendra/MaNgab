package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;
import com.stiki.mangab.model.User;

public class LoginResponse extends BaseResponse {
    @SerializedName("type")
    public String type;

    @SerializedName("no_induk")
    public String noInduk;

    @SerializedName("nama")
    public String nama;

    @SerializedName("email")
    public String email;

    @SerializedName("status_password")
    public int statusPassword;

    public User toUser(){
        User user = new User();
        user.type = type;
        user.noInduk = noInduk;
        user.nama = nama;
        user.email = email;
        user.statusPassword = statusPassword;

        return user;
    }
}
