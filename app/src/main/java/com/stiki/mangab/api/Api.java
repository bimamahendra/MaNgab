package com.stiki.mangab.api;

import com.stiki.mangab.api.response.BaseResponse;
import com.stiki.mangab.api.response.CheckStatusLoginResponse;
import com.stiki.mangab.api.response.DetailAbsenResponse;
import com.stiki.mangab.api.response.GenerateQrCodeResponse;
import com.stiki.mangab.api.response.HistoryAbsensiMhsResponse;
import com.stiki.mangab.api.response.HistoryAbsensiResponse;
import com.stiki.mangab.api.response.LoginResponse;
import com.stiki.mangab.api.response.MyClassResponse;
import com.stiki.mangab.api.response.MyLectureResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @POST("auth/checkStatusLogin")
    @FormUrlEncoded
    Call<CheckStatusLoginResponse> checkStatusLogin(@Field("id_device") String idDevice);

    @POST("auth/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("no_induk") String noInduk, @Field("password") String password, @Field("device_id") String deviceId);

    @POST("auth/logout")
    @FormUrlEncoded
    Call<BaseResponse> logout(@Field("no_induk") String noInduk);

    @POST("auth/changePassword")
    @FormUrlEncoded
    Call<BaseResponse> changePassword(@Field("no_induk") String noInduk, @Field("new_password") String newPassword);

    @POST("matkul/myLecture")
    @FormUrlEncoded
    Call<MyLectureResponse> myLecture(@Field("nip") String nip);

    @POST("matkul/myClass")
    @FormUrlEncoded
    Call<MyClassResponse> myClass(@Field("kode_matkul") String kodeMatkul);

    @POST("matkul/generateQrCode")
    @FormUrlEncoded
    Call<GenerateQrCodeResponse> generateQrCode(@Field("id_matkul") String idMatkul,
                                          @Field("topik") String topik,
                                          @Field("ruangan") String ruangan);

    @POST("absen/absenMhs")
    @FormUrlEncoded
    Call<BaseResponse> absenMhs(@Field("qr_code") String qrCode,
                          @Field("nrp") String nrp,
                          @Field("status_absen") String statusAbsen);

    @POST("absen/detailAbsen")
    @FormUrlEncoded
    Call<DetailAbsenResponse> detailAbsen(@Field("id_absen") String idAbsen);

    @POST("absen/historyAbsensiDosen")
    @FormUrlEncoded
    Call<HistoryAbsensiResponse> historyAbsensiDosen(@Field("no_induk") String noInduk);

    @POST("absen/historyAbsensiMhs")
    @FormUrlEncoded
    Call<HistoryAbsensiMhsResponse> historyAbsensiMhs(@Field("no_induk") String noInduk);

    @POST("absen/rekap")
    @FormUrlEncoded
    Call<BaseResponse> rekap(@Field("qr_code") String qrCode, @Field("note") String note);

}
